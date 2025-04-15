package org.acme.role.infra.database.repository;

import java.util.List;

import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.port.out.RoleRepository;
import org.acme.role.infra.database.entity.RoleEntity;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;

public class RoleEntityRepository implements RoleRepository {
    private final String SERVER_URL;
    private final String REALM;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    private final String ADMIN_USERNAME;
    private final String ADMIN_PASSWORD;

    public RoleEntityRepository(String serverUrl, String realm, String clientId, String clientSecret,
            String adminUsername, String adminPassword) {
        this.SERVER_URL = serverUrl;
        this.REALM = realm;
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.ADMIN_USERNAME = adminUsername;
        this.ADMIN_PASSWORD = adminPassword;
    }

    @Override
    public Role createRole(CreateRole role) {
        RoleEntity roleEntity = new RoleEntity(role);
        roleEntity.persist();
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .grantType("password")
                .build();
        RolesResource rolesResource = keycloak.realm(REALM).roles();
        var roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(role.getRoleName());
        rolesResource.create(roleRepresentation);
        return roleEntity.toRole();
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> data = RoleEntity.listAll();
        return data.stream()
                .map(RoleEntity::toRole)
                .toList();
    }

    @Override
    public Role findRoleByName(String roleName) throws RoleNotFoundException {
        RoleEntity roleEntity = RoleEntity.find("roleName", roleName).firstResult();
        if (roleEntity == null) {
            throw new RoleNotFoundException(roleName);
        }
        return roleEntity.toRole();
    }

    @Override
    public Role deleteByName(String roleName) throws RoleNotFoundException {
        RoleEntity roleEntity = RoleEntity.find("roleName", roleName).firstResult();
        if (roleEntity == null) {
            throw new RoleNotFoundException(roleName);
        }
        roleEntity.delete();

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .grantType("password")
                .build();
        RolesResource rolesResource = keycloak.realm(REALM).roles();
        rolesResource.deleteRole(roleName);

        return roleEntity.toRole();
    }

}
