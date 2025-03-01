package org.acme.cost.app;

import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.port.out.CostRepository;

import jakarta.inject.Inject;

public class CostService {
    @Inject
    CostRepository costRepository;

    public CostOutput findCostByAppId(Long appId) {
        return costRepository.findCostByAppId(appId);
    }

    public CostOutput createCost(CreateCostInput cost) {
        return costRepository.createCost(cost);
    }
}
