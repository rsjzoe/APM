package org.acme.application.infra.controller;

import java.util.ArrayList;
import java.util.List;

import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.exception.InvalidApplicationException;
import org.acme.application.domain.input.CreateApplicationRest;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.in.ApplicationRest;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.documentation.domain.DocumentationType;
import org.acme.documentation.domain.input.CreateDocumentationFileWithoutApp;
import org.acme.storage.FileInput;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@Path("/application")
public class ApplicationController implements ApplicationRest {

    @Inject
    ApplicationService applicationService;

    @GET
    @Override
    public List<ApplicationOutput> listAll() {
        return applicationService.listAll();
    }

    @GET
    @Path("/{id}")
    @Override
    public ApplicationOutput findById(@PathParam("id") Long id) {
        try {
            return applicationService.findById(id);
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @POST
    @Transactional
    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ApplicationOutput create(@RestForm List<FileUpload> files, @RestForm List<String> types,
            @RestForm @PartType(MediaType.APPLICATION_JSON) CreateApplicationRest newApplication) {

        if (files.size() != types.size()) {
            throw new BadRequestException();
        }
        List<CreateDocumentationFileWithoutApp> docs = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            CreateDocumentationFileWithoutApp doc = new CreateDocumentationFileWithoutApp();

            try {
                doc.setType(DocumentationType.valueOf(types.get(i)));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid documentation type: " + types.get(i), e);
            }
            doc.setFileInput(new FileInput(files.get(i).uploadedFile(), files.get(i).fileName()));
            docs.add(doc);
        }

        try {
            return applicationService.create(new CreateApplicationServiceInput(docs, newApplication));
        } catch (InvalidTechBusinessValueException e) {
            throw new BadRequestException(e);
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException(e);
        } catch (CategoryODAChildNotFoundException e) {
            throw new NotFoundException(e);
        } catch (DepartementNotFoundException e) {
            throw new NotFoundException(e);
        } catch (InvalidCostException e) {
            throw new BadRequestException(e);
        } catch (InvalidApplicationException e) {
            throw new BadRequestException(e);
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput update(@PathParam("id") Long id, UpdateApplicationServiceInput updateApplication) {
        try {
            return applicationService.update(id, updateApplication);
        } catch (InvalidCostException e) {
            throw new BadRequestException(e);
        } catch (InvalidTechBusinessValueException e) {
            throw new BadRequestException(e);

        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException();
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException();
        } catch (CategoryODAChildNotFoundException e) {
            throw new NotFoundException();
        } catch (DepartementNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput delete(@PathParam("id") Long id) {
        try {
            return applicationService.delete(id);
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    @GET
    @Transactional
    @Path("/deleted/list")
    public List<ApplicationOutput> deletedApplication() {
        return applicationService.deletedApplication();
    }

}
