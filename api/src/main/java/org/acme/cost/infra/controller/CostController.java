package org.acme.cost.infra.controller;

import org.acme.cost.app.CostService;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.port.in.CostRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/cost")
public class CostController implements CostRest {
    @Inject
    CostService costService;

    @Override
    @Transactional
    @Path("/{appId}")
    public CostOutput findCostByAppId(@PathParam("appId") Long appId) {
        return costService.findCostByAppId(appId);
    }
}
