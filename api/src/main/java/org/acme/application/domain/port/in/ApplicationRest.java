package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationRest;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.output.PaginationOutput;
import org.acme.application.domain.query.ApplicationQuery;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface ApplicationRest {
    PaginationOutput<ApplicationOutput> listAll(ApplicationQuery query);

    ApplicationOutput findById(Long id);

    ApplicationOutput create(List<FileUpload> files, List<String> types, CreateApplicationRest newApplication,
            String authHeader);

    ApplicationOutput update(Long id, UpdateApplicationServiceInput updateApplication, String authHeader);

    ApplicationOutput delete(Long id, String authHeader);

    List<ApplicationOutput> deletedApplication();

    ApplicationOutput restore(Long id, String authHeader);
}
