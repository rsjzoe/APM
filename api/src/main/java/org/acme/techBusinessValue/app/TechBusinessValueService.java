package org.acme.techBusinessValue.app;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class TechBusinessValueService {
    @Inject
    TechBusinessValueRepository techBusinessValueRepository;

    public List<TechBusinessValueOutput> findTechBusinessValueOutputByAppId(Long appId) {
        return techBusinessValueRepository.findTechBusinessValueByAppId(appId);
    }

    public TechBusinessValueOutput createTechBusinessValueOutput(CreateTechBusinessValue techBusinessValue) {
        return techBusinessValueRepository.createTechBusinessValue(techBusinessValue);
    }

    public TechBusinessValueOutput updateTechBusinessValueOutput(Long idTech, Long appId) {
        return techBusinessValueRepository.update(idTech, appId);
    }
}
