package org.acme.techBusinessValue.config;

import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntityRepository;

import jakarta.enterprise.inject.Produces;

public class TechBusinessValueConfig {
    @Produces
    TechBusinessValueRepository techBusinessValueRepository(){
        return new TechBusinessValueEntityRepository();
    }
}
