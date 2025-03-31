package org.acme.application.config;

import org.acme.application.app.usecase.GenerateDescription;
import org.acme.application.domain.port.GenerateDescriptionHistory;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.application.infra.database.ApplicationEntityRepository;

import jakarta.enterprise.inject.Produces;

public class ApplicationConfig {
    @Produces
    public ApplicationRepository applicationRepository() {
        return new ApplicationEntityRepository();
    }

    @Produces
    public GenerateDescriptionHistory generateDescriptionHistory() {
        return new GenerateDescription();
    }
}
