package org.acme.cost.infra.database;

import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.port.out.CostRepository;
import java.util.List;

public class CostEntityRepository implements CostRepository {

    @Override
    public List<CostOutput> findCostByAppId(Long appId) {
        List<CostEntity> data = CostEntity.list("application.id", appId);
        return data.stream().map(CostEntity::toCostOutput).toList();
    }

    @Override
    public CostOutput createCost(CreateCostInput cost) {
        CostEntity data = new CostEntity(cost);
        data.persist();
        return data.toCostOutput();
    }

    @Override
    public CostOutput update(Long idCost, Long appId) {
        CostEntity data = CostEntity.findById(idCost);
        if (data == null) {
            return null;
        }
        data.setApplication(ApplicationEntityHelper.entityFromId(appId));
        data.persist();
        return data.toCostOutput();

    }

}
