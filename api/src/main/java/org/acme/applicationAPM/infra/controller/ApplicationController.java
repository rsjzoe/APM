package org.acme.applicationAPM.infra.controller;

import java.util.List;

import org.acme.applicationAPM.app.service.ApplicationService;
import org.acme.applicationAPM.domain.input.CreateApplicationInput;
import org.acme.applicationAPM.domain.input.UpdateApplicationInput;
import org.acme.applicationAPM.domain.model.Application;
import org.acme.applicationAPM.domain.port.in.ApplicationRest;

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
    public List<Application> listAll() {
        return applicationService.listAll();
    }

    @GET
    @Path("/{id}")
    @Override
    public Application findById(@PathParam("id") Long id) {
        return applicationService.findById(id);
    }

    @POST
    @Transactional
    @Override
    public Application create(CreateApplicationInput newApplication) {
        return applicationService.create(newApplication);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Override
    public Application update(@PathParam("id") Long id, UpdateApplicationInput updateApplication) {
        return applicationService.update(id, updateApplication);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public Application delete(@PathParam("id") Long id) {
        return applicationService.delete(id);
    }

}
