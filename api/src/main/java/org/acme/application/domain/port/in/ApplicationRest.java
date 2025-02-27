package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationInput;
import org.acme.application.domain.input.UpdateApplicationInput;
import org.acme.application.domain.model.Application;

public interface ApplicationRest {
    List<Application> listAll();

    Application findById(Long id);

    Application create(CreateApplicationInput newApplication);

    Application update(Long id, UpdateApplicationInput updateApplication);

    Application delete(Long id);
}
