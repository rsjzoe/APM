package org.acme.applicationAPM.infra.controller;

import java.util.List;

import org.acme.applicationAPM.app.service.ApplicationHistoryService;
import org.acme.applicationAPM.domain.model.ApplicationHistory;
import org.acme.applicationAPM.domain.port.in.ApplicationHistoryRest;

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
    public List<ApplicationHistory> listAllByApplicationId(@PathParam("applicationId") Long applicationId) {
        return applicationHistoryService.listAllByApplicationId(applicationId);
    }
    
}
