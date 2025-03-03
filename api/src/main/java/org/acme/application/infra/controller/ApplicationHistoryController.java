package org.acme.application.infra.controller;

import java.util.List;

import org.acme.application.app.service.ApplicationHistoryService;
import org.acme.application.domain.model.output.ApplicationHistoryOutput;
import org.acme.application.domain.port.in.ApplicationHistoryRest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/application-history")
public class ApplicationHistoryController implements ApplicationHistoryRest{

    @Inject
    ApplicationHistoryService applicationHistoryService;

    @GET
    @Path("/{applicationId}")
    @Override
    public List<ApplicationHistoryOutput> listAllByApplicationId(@PathParam("applicationId") Long applicationId) {
        return applicationHistoryService.listAllByApplicationId(applicationId);
    }
    
}
