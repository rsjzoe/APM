package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.model.Application;
import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.model.output.ApplicationOutput;

public interface ApplicationRest {
    List<Application> listAll();

    ApplicationOutput findById(Long id);

    ApplicationOutput create(CreateApplicationInput newApplication);

    ApplicationOutput update(Long id, UpdateApplicationInput updateApplication);

    ApplicationOutput delete(Long id);
}
