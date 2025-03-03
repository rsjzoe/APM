package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.model.output.ApplicationOutput;

//afahana mifandray am domain 
public interface ApplicationRepository {
    List<ApplicationOutput> listAll();

    ApplicationOutput findById(Long id);

    ApplicationOutput create(CreateApplicationInput newApplication);

    ApplicationOutput update(Long id, UpdateApplicationInput updateApplication);

    ApplicationOutput delete(Long id);
}
