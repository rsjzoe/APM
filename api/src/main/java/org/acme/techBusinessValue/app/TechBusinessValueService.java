package org.acme.techBusinessValue.app;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;

import jakarta.inject.Inject;

public class TechBusinessValueService {
    @Inject
    TechBusinessValueRepository techBusinessValueRepository;

    public TechBusinessValueOutput findTechBusinessValueOutputByAppId(Long appId) {
        return techBusinessValueRepository.findTechBusinessValueByAppId(appId);
    }

    public TechBusinessValueOutput createTechBusinessValueOutput(CreateTechBusinessValue techBusinessValue) {
        return techBusinessValueRepository.createTechBusinessValue(techBusinessValue);
    }
}
