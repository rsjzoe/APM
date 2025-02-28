package org.acme.cost.domain.port.out;

import org.acme.cost.domain.model.output.CostOutput;

public interface CostRepository {
    CostOutput findCostByAppId(Long appId);
}
