package org.acme.auth.config;

import org.acme.auth.adapter.keycloak.KeycloakAuthentification;
import org.acme.auth.domain.port.out.Authentification;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class AuthConfig {
    @Inject
    @ConfigProperty(name = "keycloak.url")
    String serverUrl;

    @Inject
    @ConfigProperty(name = "keycloak.realm")
    String realm;

    @Inject
    @ConfigProperty(name = "keycloak.client-id")
    String clientId;

    @Inject
    @ConfigProperty(name = "keycloak.client-secret")
    String clientSecret;

    @Inject
    @ConfigProperty(name = "keycloak.admin.name")
    String adminName;

    @Inject
    @ConfigProperty(name = "keycloak.admin.password")
    String adminPassword;
    
    @Produces
    Authentification authentification() {
        return new KeycloakAuthentification(serverUrl, realm, clientId, clientSecret, adminName, adminPassword);
    }
}
