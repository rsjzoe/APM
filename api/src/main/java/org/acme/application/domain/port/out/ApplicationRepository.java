package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;

//afahana mifandray am domain 
public interface ApplicationRepository {
    List<ApplicationOutput> listAll();

    ApplicationOutput findById(Long id);

    ApplicationOutput create(CreateApplicationRepositoryInput newApplication);

    ApplicationOutput update(Long id, UpdateApplicationRepositoryInput updateApplication);

    ApplicationOutput delete(Long id);
}
