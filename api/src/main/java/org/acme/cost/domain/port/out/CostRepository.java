package org.acme.cost.domain.port.out;

import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;

public interface CostRepository {
    CostOutput findCostByAppId(Long appId);

    CostOutput createCost(CreateCostInput cost);

    CostOutput update(Long idCost, Long appId);

}
