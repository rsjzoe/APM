package org.acme.techBusinessValue.domain.port.out;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

public interface TechBusinessValueRepository {

    TechBusinessValueOutput findTechBusinessValueByAppId(Long appId);

    TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue);

    TechBusinessValueOutput update(Long idTech, Long appId);
}
