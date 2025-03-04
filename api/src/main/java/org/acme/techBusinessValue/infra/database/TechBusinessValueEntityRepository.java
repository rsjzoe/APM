package org.acme.techBusinessValue.infra.database;

import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;
import java.util.List;


public class TechBusinessValueEntityRepository implements TechBusinessValueRepository {

    @Override
    public List<TechBusinessValueOutput> findTechBusinessValueByAppId(Long appId) {
        List<TechBusinessValueEntity> data = TechBusinessValueEntity.list("application.id", appId);
        return data.stream().map(TechBusinessValueEntity::toTechBusinessValueOutput).toList();
    }

    @Override
    public TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue) {
        TechBusinessValueEntity data = new TechBusinessValueEntity(techBusinessValue);
        data.persist();
        return data.toTechBusinessValueOutput();
    }

    @Override
    public TechBusinessValueOutput update(Long idTech, Long appId) {

        TechBusinessValueEntity data = TechBusinessValueEntity.findById(idTech);
        if (data == null) {
            return null;
        }
        data.setApplication(ApplicationEntityHelper.entityFromId(appId));
        data.persist();
        return data.toTechBusinessValueOutput();

    }

}
