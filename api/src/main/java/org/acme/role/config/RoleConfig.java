package org.acme.role.config;

import org.acme.role.domain.port.out.RoleRepository;
import org.acme.role.infra.database.repository.RoleEntityRepository;

import jakarta.enterprise.inject.Produces;

public class RoleConfig {
    @Produces
    public RoleRepository roleRepository() {
        return new RoleEntityRepository();
    }
}
