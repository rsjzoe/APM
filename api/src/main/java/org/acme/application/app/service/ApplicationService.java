package org.acme.application.app.service;

import java.io.IOException;
import java.util.List;

import org.acme.application.app.usecase.CalculateTime;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.exception.InvalidApplicationException;
import org.acme.application.domain.input.CreateApplicationHistoryService;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.application.domain.query.ApplicationQuery;
import org.acme.category.app.CategoryODAChildService;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.classe.app.ClasseService;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.cost.app.CostService;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.departement.app.DepartementService;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.documentation.app.DocumentationService;
import org.acme.documentation.domain.input.CreateDocumentationFile;
import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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

    @Inject
    ClasseService classeService;

    @Inject
    CategoryODAChildService categoryODAChildService;

    @Inject
    DepartementService departementService;

    public List<ApplicationOutput> listAll(ApplicationQuery query) {
        return applicationRepository.listAll(query);
    }

    public ApplicationOutput findById(Long id) throws ApplicationNotFoundException {
        return applicationRepository.findById(id);
    };

    @Transactional
    public ApplicationOutput create(CreateApplicationServiceInput newApplication, String token)
            throws InvalidTechBusinessValueException, ClasseNotFoundException, CategoryODAChildNotFoundException,
            DepartementNotFoundException, InvalidCostException, InvalidApplicationException,
            ApplicationNotFoundException, VerificationTokenException, UserNotFoundException {

        classeService.findById(newApplication.getClasseId());
        categoryODAChildService.findById(newApplication.getCategoryId());
        for (Long departementId : newApplication.getDepartementIds()) {
            departementService.findByDepartementId(departementId);
        }

        Time time = null;
        if (newApplication.getTechBusinessValueWithoutApp() != null
                && newApplication.getTechBusinessValueWithoutApp().getBusinessValue() != 0
                && newApplication.getTechBusinessValueWithoutApp().getTechnicalDebt() != 0) {
            time = calculateTime.calcul(newApplication.getTechBusinessValueWithoutApp().getBusinessValue(),
                    newApplication.getTechBusinessValueWithoutApp().getTechnicalDebt());
        }

        if (newApplication.getStartDate().isAfter(newApplication.getLastUpdate())) {
            throw new InvalidApplicationException("Start date cannot be after last update date");
        }

        boolean isValidStatus = false;
        for (Status status : Status.values()) {
            if (status.name().equals(newApplication.getStatus().name())) {
                isValidStatus = true;
                break;
            }
        }

        if (!isValidStatus) {
            throw new InvalidApplicationException("Invalid status value");
        }

        ApplicationOutput created = applicationRepository
                .create(new CreateApplicationRepositoryInput(newApplication, time));

        costService.createCost(new CreateCostInput(newApplication.getCostWithoutApp().getCostBuild(),
                newApplication.getCostWithoutApp().getCostRun(), created.getId()));

        if (newApplication.getTechBusinessValueWithoutApp() != null
                && newApplication.getTechBusinessValueWithoutApp().getBusinessValue() != 0
                && newApplication.getTechBusinessValueWithoutApp().getTechnicalDebt() != 0) {
            var techBusinessValue = newApplication.getTechBusinessValueWithoutApp();
            techBusinessValueService.createTechBusinessValueOutput(new CreateTechBusinessValue(
                    techBusinessValue.getBusinessValue(), techBusinessValue.getTechnicalDebt(), created.getId()));
        }

        newApplication.getDocumentationsFileWithoutApp().forEach(
                documentation -> {
                    try {
                        documentationService.createDocumentation(new CreateDocumentationFile(
                                documentation.getFileInput(), documentation.getType(), created.getId()), token, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        var app = findById(created.getId());
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(app, token);
        applicationHistoryService.create(data);

        return created;
    };

    @Transactional
    public ApplicationOutput update(Long id, UpdateApplicationServiceInput updateApplication, String token)
            throws InvalidCostException, InvalidTechBusinessValueException, ApplicationNotFoundException,
            ClasseNotFoundException, CategoryODAChildNotFoundException, DepartementNotFoundException,
            VerificationTokenException, UserNotFoundException {

        Time time = null;
        if (updateApplication.getTechBusinessValueWithoutApp() != null) {
            time = calculateTime.calcul(updateApplication.getTechBusinessValueWithoutApp().getBusinessValue(),
                    updateApplication.getTechBusinessValueWithoutApp().getTechnicalDebt());

        }

        if (time == null && updateApplication.getNoteBusinessValue() > 0
                && updateApplication.getNoteTechnicalDebt() > 0) {
            time = calculateTime.calcul(updateApplication.getNoteBusinessValue(),
                    updateApplication.getNoteTechnicalDebt());
        }

        if (updateApplication.getClasseId() != null) {
            classeService.findById(updateApplication.getClasseId());
        }
        if (updateApplication.getCategoryId() != null) {
            categoryODAChildService.findById(updateApplication.getCategoryId());
        }
        if (updateApplication.getDepartementIds() != null) {
            for (Long departementId : updateApplication.getDepartementIds()) {
                departementService.findByDepartementId(departementId);
            }
        }

        ApplicationOutput updated = applicationRepository.update(id,
                new UpdateApplicationRepositoryInput(updateApplication, time));

        if (updateApplication.getCostWithoutApp() != null) {
            costService.createCost(new CreateCostInput(updateApplication.getCostWithoutApp().getCostBuild(),
                    updateApplication.getCostWithoutApp().getCostRun(), updated.getId()));
        }
        if (updateApplication.getTechBusinessValueWithoutApp() != null) {
            var techBusinessValue = updateApplication.getTechBusinessValueWithoutApp();
            techBusinessValueService.createTechBusinessValueOutput(new CreateTechBusinessValue(
                    techBusinessValue.getBusinessValue(), techBusinessValue.getTechnicalDebt(), updated.getId()));

        }

        var app = findById(id);
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(app, token,
                updateApplication.getOtherDescription());
        applicationHistoryService.create(data);
        // maka anlay app updated miarakam curreent cost any vaovao
        ApplicationOutput appFound = findById(id);
        return appFound;
    };

    public ApplicationOutput delete(Long id, String token)
            throws ApplicationNotFoundException, VerificationTokenException, UserNotFoundException {
        ApplicationOutput deleted = applicationRepository.delete(id);
        var app = findById(id);
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(app, token);
        applicationHistoryService.create(data);
        return deleted;
    };

    public List<ApplicationOutput> deletedApplication() {
        return applicationRepository.deletedApplication();
    }

    public ApplicationOutput restore(Long id, String token)
            throws ApplicationNotFoundException, VerificationTokenException, UserNotFoundException {
        ApplicationOutput restored = applicationRepository.restore(id);
        var app = findById(id);
        CreateApplicationHistoryService data = new CreateApplicationHistoryService(app, token,
                "L'application a été restaurée.");
        applicationHistoryService.create(data);
        return restored;
    }
}
