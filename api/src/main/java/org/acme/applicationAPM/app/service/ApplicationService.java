package org.acme.applicationAPM.app.service;

import java.util.List;

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

    public List<Application> listAll() {
        return applicationRepository.listAll();
    };

    public Application findById(Long id) {
        return applicationRepository.findById(id);
    };

    public Application create(CreateApplicationInput newApplication) {
        return applicationRepository.create(newApplication);
    };

    public Application update(Long id, UpdateApplicationInput updateApplication) {
        return applicationRepository.update(id, updateApplication);
    };

    public Application delete(Long id) {
        return applicationRepository.delete(id);
    };
}
