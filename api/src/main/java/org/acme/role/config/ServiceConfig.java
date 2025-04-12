package org.acme.role.config;

import org.acme.role.domain.port.out.ServiceRepository;
import org.acme.role.infra.database.repository.ServiceEntityRepository;

import jakarta.enterprise.inject.Produces;

public class ServiceConfig {
    @Produces
    public static ServiceRepository serviceRepository() {
        return new ServiceEntityRepository();
    }
}
