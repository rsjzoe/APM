package org.acme.application.app.service;

import java.io.IOException;
import java.util.List;

import org.acme.application.app.usecase.CalculateTime;
import org.acme.application.domain.input.CreateApplicationHistoryService;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.cost.app.CostService;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.documentation.app.DocumentationService;
import org.acme.documentation.domain.input.CreateDocumentationFile;
import org.acme.storage.FileNotFound;
import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;

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

    @Inject
    DocumentationService documentationService;

    public List<ApplicationOutput> listAll() {
        return applicationRepository.listAll();
    };

    public ApplicationOutput findById(Long id) {
        return applicationRepository.findById(id);
    };

    public ApplicationOutput create(CreateApplicationServiceInput newApplication) {
        // newApplication.setTime(calculateTime.calcul(newApplication.getBusinessValue(),
        // newApplication.getCostBuild() + newApplication.getCostRun()));

        ApplicationOutput created = applicationRepository.create(new CreateApplicationRepositoryInput(newApplication));
        costService.createCost(new CreateCostInput(newApplication.getCostWithoutApp().getCostBuild(),
                newApplication.getCostWithoutApp().getCostRun(), created.getId()));
        var techBusinessValue = newApplication.getTechBusinessValueWithoutApp();
        techBusinessValueService.createTechBusinessValueOutput(new CreateTechBusinessValue(
                techBusinessValue.getBusinessValue(), techBusinessValue.getTechnicalDebt(), created.getId()));
        newApplication.getDocumentationsFileWithoutApp().forEach(
                documentation -> {
                    try {
                        documentationService.createDocumentation(new CreateDocumentationFile(
                                documentation.getFileInput(), documentation.getType(), created.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (FileNotFound e) {
                        e.printStackTrace();
                    }
                });
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(created.getId());
        applicationHistoryService.create(data);

        return created;
    };

    public ApplicationOutput update(Long id, UpdateApplicationServiceInput updateApplication) {
        ApplicationOutput updated = applicationRepository.update(id,
                new UpdateApplicationRepositoryInput(updateApplication));
        if (updateApplication.getCostWithoutApp() != null) {
            costService.createCost(new CreateCostInput(updateApplication.getCostWithoutApp().getCostBuild(),
                    updateApplication.getCostWithoutApp().getCostRun(), updated.getId()));
        }
        if (updateApplication.getTechBusinessValueWithoutApp() != null) {
            var techBusinessValue = updateApplication.getTechBusinessValueWithoutApp();
            techBusinessValueService.createTechBusinessValueOutput(new CreateTechBusinessValue(
                    techBusinessValue.getBusinessValue(), techBusinessValue.getTechnicalDebt(), updated.getId()));

        }
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(id);
        applicationHistoryService.create(data);
        // maka anlay app updated miarakam curreent cost any vaovao
        ApplicationOutput appFound = findById(id);
        return appFound;
    };

    public ApplicationOutput delete(Long id) {
        return applicationRepository.delete(id);
    };
}
