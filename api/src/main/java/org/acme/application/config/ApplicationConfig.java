package org.acme.application.config;

import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.application.infra.database.ApplicationEntityRepository;

import jakarta.enterprise.inject.Produces;

public class ApplicationConfig {
    @Produces
    public ApplicationRepository applicationRepository() {
        return new ApplicationEntityRepository();
    }
}
