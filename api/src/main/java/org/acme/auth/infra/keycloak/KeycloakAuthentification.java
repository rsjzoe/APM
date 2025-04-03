package org.acme.auth.infra.keycloak;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.auth.domain.port.out.Authentification;
import org.acme.user.domain.UserOutput;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.core.Response;

public class KeycloakAuthentification implements Authentification {

    private final String SERVER_URL;
    private final String REALM;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    private final String ADMIN_USERNAME;
    private final String ADMIN_PASSWORD;
    private String prefix = "-orange";

    public KeycloakAuthentification(String serverUrl, String realm, String clientId, String clientSecret,
            String adminUsername, String adminPassword) {
        this.SERVER_URL = serverUrl;
        this.REALM = realm;
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.ADMIN_USERNAME = adminUsername;
        this.ADMIN_PASSWORD = adminPassword;
    }

    @Override
    public Token login(Login login) throws LoginException {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .grantType("password")
                    .username(login.getTrigramme() + prefix)
                    .password(login.getPassword())
                    .build();

            // m genere token
            AccessTokenResponse tokenResponse = keycloak.tokenManager().grantToken();
            return new Token(tokenResponse.getToken(), tokenResponse.getRefreshToken(), tokenResponse.getExpiresIn(),
                    tokenResponse.getRefreshExpiresIn());

        } catch (Exception e) {
            // m throw error satria tsy anjarany ny m gerer anzai fa n any ivelany .. izay
            // mapiasa azy
            throw new LoginException();
        }
    }

    @Override
    public UserOutput register(Register register) throws UserExistedException, UserCreatedException {
        // Connexion en tant qu'administrateur pour créer l'utilisateur
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .grantType("password")
                .build();

        // Récupérer la ressource des utilisateurs
        UsersResource usersResource = keycloak.realm(REALM).users();
        // Vérifier si l'utilisateur existe déjà
        if (!usersResource.search(register.getTrigramme() + prefix).isEmpty()) {
            throw new UserExistedException();
        }

        // Création de l'utilisateur
        UserRepresentation user = new UserRepresentation();

        user.setUsername(register.getTrigramme() + prefix);
        user.setEmail(register.getTrigramme() + "orange@gmail.com");
        user.setEnabled(true);
        user.setFirstName(register.getName());
        user.setLastName(register.getDepartement());

        // Ajouter l'utilisateur
        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            // 201 -> crreateed
            throw new UserCreatedException();
        }

        // Récupérer l'ID de l'utilisateur créé
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Définir le mot de passe
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("0000");

        usersResource.get(userId).resetPassword(passwordCred);

        RolesResource rolesResource = keycloak.realm(REALM).roles();
        RoleRepresentation userRole = rolesResource.get(register.getRole().toString()).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(userRole));

        return new UserOutput(register.getName(), register.getTrigramme(), register.getDepartement(),
                register.getRole());
    }

    @Override
    public Token refreshToken(String refreshToken) throws LoginException, Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // Construire les paramètres du formulaire
            Map<String, String> parameters = Map.of(
                    "client_id", CLIENT_ID,
                    "client_secret", CLIENT_SECRET,
                    "grant_type", "refresh_token",
                    "refresh_token", refreshToken);

            String form = parameters.entrySet().stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                            + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            // Construire la requête HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(form))
                    .build();

            // Envoyer la requête et récupérer la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new LoginException();
            }

            // Parser la réponse JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.body());

            Token data = new Token(
                    jsonResponse.get("access_token").asText(),
                    jsonResponse.get("refresh_token").asText(),
                    jsonResponse.get("expires_in").asInt(),
                    jsonResponse.get("refresh_expires_in").asInt());
            return data;

        } catch (Exception e) {
            throw new Exception();
        }
    }
}
