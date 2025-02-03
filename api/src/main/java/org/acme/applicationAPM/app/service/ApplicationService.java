package org.acme.applicationAPM.app.service;

import java.util.List;

import org.acme.applicationAPM.app.usecase.CalculateTime;
import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.input.CreateApplicationInput;
import org.acme.applicationAPM.domain.input.UpdateApplicationInput;
import org.acme.applicationAPM.domain.model.Application;
import org.acme.applicationAPM.domain.port.out.ApplicationRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationService {
    @Inject
    ApplicationRepository applicationRepository;

    @Inject
    ApplicationHistoryService applicationHistoryService;

    @Inject
    CalculateTime calculateTime;

    public List<Application> listAll() {
        return applicationRepository.listAll();
    };

    public Application findById(Long id) {
        return applicationRepository.findById(id);
    };

    public Application create(CreateApplicationInput newApplication) {
        newApplication.setTime(calculateTime.calcul(newApplication.getBusinessValue(),
                newApplication.getCostBuild() + newApplication.getCostRun()));

        Application created = applicationRepository.create(newApplication);
        applicationHistoryService.create(new CreateApplicationHistoryInput(
                created.getName(),
                created.getDescription(),
                created.getBusinessValue(),
                created.getCostBuild(),
                created.getCostRun(),
                created.getStartDate(),
                created.getLastUpdate(),
                created.getStatus(),
                created.getTime(),
                created.getUserTotal(),
                "system",
                created.getId(),
                created.getNote(),
                created.getCategory(),
                created.getDepartement()));

        return created;
    };

    public Application update(Long id, UpdateApplicationInput updateApplication) {
        Application updated = applicationRepository.update(id, updateApplication);
        applicationHistoryService.create(new CreateApplicationHistoryInput(
                updated.getName(),
                updated.getDescription(),
                updated.getBusinessValue(),
                updated.getCostBuild(),
                updated.getCostRun(),
                updated.getStartDate(),
                updated.getLastUpdate(),
                updated.getStatus(),
                updated.getTime(),
                updated.getUserTotal(),
                "system",
                updated.getId(),
                updated.getNote(),
                updated.getCategory(),
                updated.getDepartement()));
        return updated;
    };

    public Application delete(Long id) {
        return applicationRepository.delete(id);
    };
}
