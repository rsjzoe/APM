package org.acme.user.config;

import org.acme.role.domain.port.out.RoleRepository;
import org.acme.user.domain.port.out.UserRepository;
import org.acme.user.infra.database.UserKeycloak;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class UserConfig {
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

    @Inject
    RoleRepository roleRepository;

    @Produces
    UserRepository userRepository() {
        return new UserKeycloak(serverUrl, realm, clientId, clientSecret, adminName, adminPassword, roleRepository);
    }
}
