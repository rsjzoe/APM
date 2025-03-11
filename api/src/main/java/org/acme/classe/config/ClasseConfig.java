package org.acme.classe.config;

import org.acme.classe.domain.port.out.ClasseRepository;
import org.acme.classe.infra.database.ClasseEntityRepository;

import jakarta.enterprise.inject.Produces;

public class ClasseConfig {
    @Produces
    public ClasseRepository classeRepository() {
        return new ClasseEntityRepository();
    };
}
