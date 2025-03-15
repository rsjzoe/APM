package org.acme.cost.app;

import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.port.out.CostRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CostService {
    @Inject
    CostRepository costRepository;

    public List<CostOutput> findCostByAppId(Long appId) {
        return costRepository.findCostByAppId(appId);
    }

    public CostOutput createCost(CreateCostInput cost) throws InvalidCostException {
        if (!cost.checkIfValid()) {
            throw new InvalidCostException();
        }
        return costRepository.createCost(cost);
    }

    public CostOutput updateCost(Long idCost, Long appId) {
        return costRepository.update(idCost, appId);
    }
}
