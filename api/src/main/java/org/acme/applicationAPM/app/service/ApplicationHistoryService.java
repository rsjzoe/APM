package org.acme.applicationAPM.app.service;

import java.util.List;

import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;
import org.acme.applicationAPM.domain.port.out.ApplicationHistoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationHistoryService {

    @Inject
    ApplicationHistoryRepository repository;

    public List<ApplicationHistory> listAllByApplicationId(Long applicationId) {
        return repository.listAllByApplicationId(applicationId);
    };

    public ApplicationHistory findById(Long id) {
        return repository.findById(id);
    };

    public ApplicationHistory create(CreateApplicationHistoryInput newdata) {
        return repository.create(newdata);
    };

    public ApplicationHistory delete(Long id) {
        return repository.delete(id);
    };
}
