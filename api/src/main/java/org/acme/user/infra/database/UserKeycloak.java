package org.acme.user.infra.database;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.acme.role.domain.model.Role;
import org.acme.role.domain.port.out.RoleRepository;
import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.port.out.UserRepository;
import org.keycloak.TokenVerifier;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class UserKeycloak implements UserRepository {

    Keycloak keycloak;

    private final String SERVER_URL;
    private final String REALM;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    private final String ADMIN_USERNAME;
    private final String ADMIN_PASSWORD;
    private String prefix = "-orange";

    private RoleRepository roleRepository;

    public UserKeycloak(String serverUrl, String realm, String clientId, String clientSecret,
            String adminUsername, String adminPassword, RoleRepository roleRepository) {
        this.SERVER_URL = serverUrl;
        this.REALM = realm;
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.ADMIN_USERNAME = adminUsername;
        this.ADMIN_PASSWORD = adminPassword;
        this.roleRepository = roleRepository;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .grantType("password")
                .build();
    }

    @Override
    public UserOutput me(String token) throws VerificationTokenException, UserNotFoundException {

        try {
            // maka ny info miafina ao ambadikka , ao amle tokeen(mdecoder anle token)
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();
            String trigramme = accessToken.getPreferredUsername().replace(prefix, "");
            UserRepresentation user = findByTrigramme(trigramme);

            return userRepresentationToOutput(user);
        } catch (VerificationException e) {
            throw new VerificationTokenException();
        }
    }

    @Override
    public List<UserOutput> findAllUser() {
        UsersResource usersResource = keycloak.realm(REALM).users();

        List<UserOutput> users = usersResource.list().stream()
                .map(user -> {
                    return userRepresentationToOutput(user);
                })
                .collect(Collectors.toList());

        return users;

    }

    public UserRepresentation findByTrigramme(String trigramme) throws UserNotFoundException {
        UsersResource usersResource = keycloak.realm(REALM).users();

        // Rechercher l'utilisateur par trigramme
        List<UserRepresentation> users = usersResource.list().stream()
                .filter(user -> {
                    return user.getUsername().equalsIgnoreCase(trigramme + prefix);
                }) // Filtrer par trigramme
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            throw new UserNotFoundException("Aucun utilisateur trouvé avec le trigramme : " + trigramme);
        }
        UserRepresentation userFound = users.get(0);
        return userFound;
    }

    @Override
    public UserOutput deleteByTrigramme(String trigramme) throws UserNotFoundException {
        UsersResource usersResource = keycloak.realm(REALM).users();

        UserRepresentation userToDelete = findByTrigramme(trigramme);
        usersResource.get(userToDelete.getId()).remove();
        return new UserOutput(
                userToDelete.getFirstName(),
                userToDelete.getUsername().replace(prefix, ""),
                userToDelete.getLastName(),
                null);
    }

    @Override
    public UserOutput updateByTrigramme(String trigramme, UpdateUser userUpdate) throws UserNotFoundException {
        UsersResource usersResource = keycloak.realm(REALM).users();

        // Rechercher l'utilisateur par trigramme (en supposant que c'est stocké dans le
        // prénom)
        List<UserRepresentation> users = usersResource.list().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(trigramme + prefix)) // Filtrer par trigramme
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            throw new UserNotFoundException("Aucun utilisateur trouvé avec le trigramme : " + trigramme);
        }

        UserRepresentation userToUpdate = users.get(0); // Supposons qu'il est unique
        String userId = userToUpdate.getId();

        // Mise à jour des informations
        if (userUpdate.getName() != null) {
            userToUpdate.setFirstName(userUpdate.getName());
        }
        if (userUpdate.getDepartement() != null) {
            userToUpdate.setLastName(userUpdate.getDepartement());
        }

        // Appliquer la mise à jour
        usersResource.get(userId).update(userToUpdate);

        if (userUpdate.getRole() != null) {
            // Suppression des anciens rôles
            usersResource.get(userId).roles().realmLevel()
                    .remove(usersResource.get(userId).roles().realmLevel().listEffective());

            // Ajout du nouveau rôle
            RoleRepresentation newRole = keycloak.realm(REALM).roles().get(userUpdate.getRole())
                    .toRepresentation();
            usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(newRole));
        }

        return userRepresentationToOutput(userToUpdate);
    }

    public UserOutput userRepresentationToOutput(UserRepresentation user) {
        UsersResource usersResource = keycloak.realm(REALM).users();

        List<String> roles = usersResource.get(user.getId())
                .roles()
                .realmLevel()
                .listAll()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        List<Role> rolesDatabase = roleRepository.findAll();

        String roleString = roles.stream()
                .filter(role -> rolesDatabase.stream()
                        .anyMatch(dbRole -> dbRole.getRoleName().equalsIgnoreCase(role)))
                .findFirst()
                .orElse("visitor");

        return new UserOutput(
                user.getFirstName(),
                user.getUsername().replace(prefix, ""),
                roleString, user.getLastName());
    }
}
