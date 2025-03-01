package org.acme.cost.infra.database;

import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.port.out.CostRepository;

public class CostEntityRepository implements CostRepository {

    @Override
    public CostOutput findCostByAppId(Long appId) {
        CostEntity data = CostEntity.findById(appId);
        if (data == null)
            return null;
        return data.toCostOutput();
    }

    @Override
    public CostOutput createCost(CreateCostInput cost) {
        CostEntity data = new CostEntity(cost);
        data.persist();
        return data.toCostOutput();
    }

}
