package org.acme.role.config;

import org.acme.role.domain.port.out.RoleRepository;
import org.acme.role.infra.database.repository.RoleEntityRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class RoleConfig {
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
    public RoleRepository roleRepository() {
        return new RoleEntityRepository(serverUrl, realm, clientId, clientSecret, adminName, adminPassword);
    }
}
