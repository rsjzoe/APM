package org.acme.techBusinessValue.domain.port.in;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

public interface TechBusinessValueRest {
    TechBusinessValueOutput findTechBusinessValueByAppId(Long appId);

    TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue);

}
