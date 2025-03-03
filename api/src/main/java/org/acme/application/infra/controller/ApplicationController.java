package org.acme.application.infra.controller;

import java.util.List;

import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.model.output.ApplicationOutput;
import org.acme.application.domain.port.in.ApplicationRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

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
    public ApplicationOutput create(CreateApplicationInput newApplication) {
        return applicationService.create(newApplication);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput update(@PathParam("id") Long id, UpdateApplicationInput updateApplication) {
        return applicationService.update(id, updateApplication);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationOutput delete(@PathParam("id") Long id) {
        return applicationService.delete(id);
    }

}
