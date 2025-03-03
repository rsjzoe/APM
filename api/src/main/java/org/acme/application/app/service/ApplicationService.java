package org.acme.application.app.service;

import java.util.List;

import org.acme.application.app.usecase.CalculateTime;
import org.acme.application.domain.model.input.CreateApplicationHistoryService;
import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.model.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.cost.app.CostService;
import org.acme.cost.domain.port.out.CostRepository;
import org.acme.techBusinessValue.app.TechBusinessValueService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationService {
    @Inject
    ApplicationRepository applicationRepository;

    @Inject
    ApplicationHistoryService applicationHistoryService;

    @Inject
    CostService costService;

    @Inject
    TechBusinessValueService techBusinessValueService;

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
        costService.updateCost(newApplication.getCostId(), created.getId());
        techBusinessValueService.updateTechBusinessValueOutput(newApplication.getTechBusinessValueId(), created.getId());
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(created.getId());
        applicationHistoryService.create(data);

        return created;
    };

    public ApplicationOutput update(Long id, UpdateApplicationInput updateApplication) {
        // updateApplication.setTime(calculateTime.calcul(updateApplication.getBusinessValue(),
        // updateApplication.getCostBuild() + updateApplication.getCostRun()));
        ApplicationOutput updated = applicationRepository.update(id, updateApplication);
        // costService.updateCost(updated.getCostId(), created.getId());
        // techBusinessValueService.updateTechBusinessValueOutput(newApplication.getTechBusinessValueId(), created.getId());
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(id);
        applicationHistoryService.create(data);
        return updated;
    };

    public ApplicationOutput delete(Long id) {
        return applicationRepository.delete(id);
    };
}
