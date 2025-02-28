package org.acme.cost.domain.port.in;

import org.acme.cost.domain.model.output.CostOutput;

public interface CostRest {
    CostOutput findCostByAppId(Long appId);
}
