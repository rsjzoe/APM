package org.acme.techBusinessValue.app;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TechBusinessValueService {
    @Inject
    TechBusinessValueRepository techBusinessValueRepository;

    @Inject
    ApplicationRepository applicationRepository;

    public List<TechBusinessValueOutput> findTechBusinessValueOutputByAppId(Long appId) {
        return techBusinessValueRepository.findTechBusinessValueByAppId(appId);
    }

    public TechBusinessValueOutput createTechBusinessValueOutput(CreateTechBusinessValue techBusinessValue)
            throws InvalidTechBusinessValueException, ApplicationNotFoundException {
        if (!techBusinessValue.checkIfValid()) {
            throw new InvalidTechBusinessValueException();
        }
        if (techBusinessValue.getAppId() != null) {
            applicationRepository.findById(techBusinessValue.getAppId());
        }
        return techBusinessValueRepository.createTechBusinessValue(techBusinessValue);
    }

    public TechBusinessValueOutput updateTechBusinessValueOutput(Long idTech, Long appId)
            throws ApplicationNotFoundException {
        applicationRepository.findById(appId);
        return techBusinessValueRepository.update(idTech, appId);
    }

    public List<TechBusinessValueMonth> findTechBusinessValueLatestPerMonthByAppId(Long appId, int year) {
        return techBusinessValueRepository.findTechBusinessValueLatestPerMonthByAppId(appId, year);
    }
}
