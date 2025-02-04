package org.acme.applicationAPM.infra.controller;

import java.util.List;

import org.acme.applicationAPM.app.service.ApplicationHistoryService;
import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;
import org.acme.applicationAPM.domain.port.in.ApplicationHistoryRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/application-history")
public class ApplicationHistoryController implements ApplicationHistoryRest {

    @Inject
    ApplicationHistoryService applicationHistoryService;

    @GET
    @Override
    public List<ApplicationHistory> listAll() {
        return applicationHistoryService.listAll();
    }

    @GET
    @Path("/{id}")
    @Override
    public ApplicationHistory findById(@PathParam("id") Long id) {
        return applicationHistoryService.findById(id);
    }

    @POST
    @Transactional
    @Override
    public ApplicationHistory create(CreateApplicationHistoryInput applicationHistory) {
        return applicationHistoryService.create(applicationHistory);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public ApplicationHistory delete(@PathParam("id") Long id) {
        return applicationHistoryService.delete(id);
    }

}
