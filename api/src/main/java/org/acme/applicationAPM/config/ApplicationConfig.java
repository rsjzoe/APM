package org.acme.applicationAPM.config;

import org.acme.applicationAPM.domain.port.out.ApplicationRepository;
import org.acme.applicationAPM.infra.database.ApplicationEntityRepository;

import jakarta.enterprise.inject.Produces;

public class ApplicationConfig {
    @Produces
    public ApplicationRepository applicationRepository() {
        return new ApplicationEntityRepository();
    }
}
