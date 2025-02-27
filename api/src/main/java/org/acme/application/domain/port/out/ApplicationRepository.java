package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationInput;
import org.acme.application.domain.input.UpdateApplicationInput;
import org.acme.application.domain.model.Application;

//afahana mifandray am domain 
public interface ApplicationRepository {
    List<Application> listAll();

    Application findById(Long id);

    Application create(CreateApplicationInput newApplication);

    Application update(Long id, UpdateApplicationInput updateApplication);

    Application delete(Long id);
}
