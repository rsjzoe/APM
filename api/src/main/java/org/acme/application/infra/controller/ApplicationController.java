package org.acme.application.infra.controller;

import java.util.ArrayList;
import java.util.List;

import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.input.CreateApplicationRest;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.in.ApplicationRest;
import org.acme.category.domain.exception.CategoryChildNotFoundException;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.documentation.domain.DocumentationType;
import org.acme.documentation.domain.input.CreateDocumentationFileWithoutApp;
import org.acme.storage.FileInput;
import org.acme.techBusinessValue.domain.exception.TechBusinessValueNotValidException;
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
        return applicationService.findById(id);
    }

    @POST
    @Transactional
    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ApplicationOutput create(@RestForm List<FileUpload> files, @RestForm List<String> types,
            @RestForm @PartType(MediaType.APPLICATION_JSON) CreateApplicationRest newApplication) {

        List<CreateDocumentationFileWithoutApp> docs = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            CreateDocumentationFileWithoutApp doc = new CreateDocumentationFileWithoutApp();
            doc.setType(DocumentationType.valueOf(types.get(i)));
            doc.setFileInput(new FileInput(files.get(i).uploadedFile(), files.get(i).fileName()));
            docs.add(doc);
        }

        try {
            return applicationService.create(new CreateApplicationServiceInput(docs, newApplication));
        } catch (TechBusinessValueNotValidException e) {
            throw new BadRequestException(e);
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException(e);
        } catch (CategoryChildNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput update(@PathParam("id") Long id, UpdateApplicationServiceInput updateApplication) {
        return applicationService.update(id, updateApplication);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput delete(@PathParam("id") Long id) {
        return applicationService.delete(id);
    }

    @Override
    @GET
    @Transactional
    @Path("/deleted/list")
    public List<ApplicationOutput> deletedApplication() {
        return applicationService.deletedApplication();
    }

}
