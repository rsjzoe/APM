package org.acme.application.app.service;

import java.util.List;

import org.acme.application.app.usecase.CalculateTime;
import org.acme.application.domain.model.Application;
import org.acme.application.domain.model.input.CreateApplicationHistoryInput;
import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.model.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;

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

    public List<ApplicationOutput> listAll() {
        return applicationRepository.listAll();
    };

    public ApplicationOutput findById(Long id) {
        return applicationRepository.findById(id);
    };

    public ApplicationOutput create(CreateApplicationInput newApplication) {
        // newApplication.setTime(calculateTime.calcul(newApplication.getBusinessValue(),
        // newApplication.getCostBuild() + newApplication.getCostRun()));

        ApplicationOutput created = applicationRepository.create(newApplication);
        applicationHistoryService.create(new CreateApplicationHistoryInput(null, null, null));

        return created;
    };

    public ApplicationOutput update(Long id, UpdateApplicationInput updateApplication) {
        // updateApplication.setTime(calculateTime.calcul(updateApplication.getBusinessValue(),
        // updateApplication.getCostBuild() + updateApplication.getCostRun()));
        ApplicationOutput updated = applicationRepository.update(id, updateApplication);
        applicationHistoryService.create(new CreateApplicationHistoryInput(
                updated.getName(),
                updated.getDescription(),
                updated.getStartDate(),
                updated.getLastUpdate(),
                updated.getStatus(),
                updated.getTime(),
                updated.getUserTotal(),
                "system",
                updated.getId(),
                updated.getNote(),
                updated.getCategory(),
                updated.getDepartement(),
                updated.getCost(),
                updated.getTechBusinessValue()));
        return updated;
    };

    public ApplicationOutput delete(Long id) {
        return applicationRepository.delete(id);
    };
}
