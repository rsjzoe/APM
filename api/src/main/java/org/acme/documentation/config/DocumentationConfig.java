package org.acme.documentation.config;

import org.acme.documentation.adapter.out.DocumentationEntityRepository;
import org.acme.documentation.domain.ports.out.DocumentationRepository;

import jakarta.enterprise.inject.Produces;

public class DocumentationConfig {
    @Produces
    public DocumentationRepository documentationRepository() {
        return new DocumentationEntityRepository();
    }
}
