package org.acme.application.app.service;

import java.util.List;

import org.acme.application.domain.exception.ApplicationHistoryNotFoundException;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.input.CreateApplicationHistoryService;
import org.acme.application.domain.output.ApplicationHistoryOutput;
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

    public ApplicationHistoryOutput findById(Long id) throws ApplicationHistoryNotFoundException {
        return repository.findById(id);
    };

    public ApplicationHistoryOutput create(CreateApplicationHistoryService newdata)
            throws ApplicationNotFoundException {
        System.out.println(newdata.getApp());

        List<ApplicationHistoryOutput> historyList = listAllByApplicationId(newdata.getApp().getId());
        ApplicationHistoryOutput lastHistory = historyList.stream()
                .max((h1, h2) -> h1.getModifiedAt().compareTo(h2.getModifiedAt()))
                .orElse(null);
        System.out.println("Last History: " + lastHistory);
        CreateApplicationHistoryRepository data = new CreateApplicationHistoryRepository(newdata.getApp(), "systeem",
                "okokok");
        return repository.create(data);
    };

}
