package org.acme.techBusinessValue.infra.database;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;

public class TechBusinessValueEntityRepository implements TechBusinessValueRepository {

    @Override
    public TechBusinessValueOutput findTechBusinessValueByAppId(Long appId) {
        TechBusinessValueEntity data = TechBusinessValueEntity.findById(appId);
        if (data == null)
            return null;
        return data.toTechBusinessValueOutput();
    }

    @Override
    public TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue) {
        TechBusinessValueEntity data = new TechBusinessValueEntity(techBusinessValue);
        data.persist();
        return data.toTechBusinessValueOutput();
    }

}
