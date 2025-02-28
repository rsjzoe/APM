package org.acme.cost.config;

import org.acme.cost.domain.port.out.CostRepository;
import org.acme.cost.infra.database.CostEntityRepository;

import jakarta.enterprise.inject.Produces;

public class CostConfig {
    @Produces
    public CostRepository costRepository() {
        return new CostEntityRepository();
    }
}
