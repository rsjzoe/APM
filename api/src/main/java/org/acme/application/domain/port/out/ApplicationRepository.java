package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;

//afahana mifandray am domain 
public interface ApplicationRepository {
    List<ApplicationOutput> listAll();

    ApplicationOutput findById(Long id) throws ApplicationNotFoundException;

    ApplicationOutput create(CreateApplicationRepositoryInput newApplication);

    ApplicationOutput update(Long id, UpdateApplicationRepositoryInput updateApplication)
            throws ApplicationNotFoundException;

    ApplicationOutput delete(Long id) throws ApplicationNotFoundException;

    List<ApplicationOutput> deletedApplication();
}
