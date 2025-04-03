package org.acme.cost.app;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;
import org.acme.cost.domain.port.out.CostRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CostService {
    @Inject
    CostRepository costRepository;

    @Inject
    ApplicationRepository applicationRepository;

    public List<CostOutput> findCostByAppId(Long appId) {
        return costRepository.findCostByAppId(appId);
    }

    public CostOutput createCost(CreateCostInput cost) throws InvalidCostException, ApplicationNotFoundException {
        if (!cost.checkIfValid()) {
            throw new InvalidCostException();
        }
        if (cost.getApplicationId() != null) {
            applicationRepository.findById(cost.getApplicationId());
        }
        return costRepository.createCost(cost);
    }

    public CostOutput updateCost(Long idCost, Long appId) throws ApplicationNotFoundException {
        applicationRepository.findById(appId);
        return costRepository.update(idCost, appId);
    }

    public List<CostOutputMonth> findCostLatestPerMonthByAppId(Long appId) {
        return costRepository.findCostLatestPerMonthByAppId(appId);
    }
}
