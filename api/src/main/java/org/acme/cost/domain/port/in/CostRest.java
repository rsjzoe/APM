package org.acme.cost.domain.port.in;

import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;

import java.util.List;

public interface CostRest {
    List<CostOutput> findCostByAppId(Long appId);

    CostOutput createCost(CreateCostInput cost);

    List<CostOutputMonth> findCostLatestPerMonthByAppId(Long appId, int year);

}
