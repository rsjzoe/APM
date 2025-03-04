package org.acme.techBusinessValue.domain.port.in;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import java.util.List;


public interface TechBusinessValueRest {
    List<TechBusinessValueOutput> findTechBusinessValueByAppId(Long appId);

    TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue);

}
