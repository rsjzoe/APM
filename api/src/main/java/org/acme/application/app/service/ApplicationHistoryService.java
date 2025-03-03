package org.acme.application.app.service;

import java.util.List;

import org.acme.application.domain.model.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.model.input.CreateApplicationHistoryService;
import org.acme.application.domain.model.output.ApplicationHistoryOutput;
import org.acme.application.domain.model.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationHistoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationHistoryService {

    @Inject
    ApplicationHistoryRepository repository;

    @Inject
    ApplicationService applicationService;

    public List<ApplicationHistoryOutput> listAllByApplicationId(Long applicationId) {
        return repository.listAllByApplicationId(applicationId);
    };

    public ApplicationHistoryOutput findById(Long id) {
        return repository.findById(id);
    };

    public ApplicationHistoryOutput create(CreateApplicationHistoryService newdata) {
        ApplicationOutput app = applicationService.findById(newdata.getAppId());
        CreateApplicationHistoryRepository data = new CreateApplicationHistoryRepository(app, "systeem", "okokok"); 
        return repository.create(data);
    };

    public ApplicationHistoryOutput delete(Long id) {
        return repository.delete(id);
    };
}
