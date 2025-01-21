package org.acme.applicationAPM.app.service;

import java.util.List;

import org.acme.applicationAPM.app.usecase.CalculateTime;
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
        return applicationRepository.create(newApplication);
    };

    public Application update(Long id, UpdateApplicationInput updateApplication) {
        return applicationRepository.update(id, updateApplication);
    };

    public Application delete(Long id) {
        return applicationRepository.delete(id);
    };
}
