package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationRest;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface ApplicationRest {
    List<ApplicationOutput> listAll();

    ApplicationOutput findById(Long id);

    ApplicationOutput create(List<FileUpload> files, List<String> types, CreateApplicationRest newApplication);

    ApplicationOutput update(Long id, UpdateApplicationServiceInput updateApplication);

    ApplicationOutput delete(Long id);

    List<ApplicationOutput> deletedApplication();

}
