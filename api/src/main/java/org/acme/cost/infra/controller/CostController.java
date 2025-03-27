package org.acme.cost.infra.controller;

import org.acme.cost.app.CostService;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;
import org.acme.cost.domain.port.in.CostRest;
import org.acme.roleGuard.RoleAllowedCustom;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;

@Path("/cost")
@Authenticated
public class CostController implements CostRest {
    @Inject
    CostService costService;

    @Override
    @Transactional
    @Path("/{appId}")
    @GET
    public List<CostOutput> findCostByAppId(@PathParam("appId") Long appId) {
        return costService.findCostByAppId(appId);
    }

    @Override
    @Transactional
    @POST
    public CostOutput createCost(CreateCostInput cost) {
        try {
            return costService.createCost(cost);
        } catch (InvalidCostException e) {
            throw new BadRequestException();
        }
    }

    @Override
    @Transactional
    @GET
    @Path("/latest-per-month/{appId}")
    @RoleAllowedCustom({ "admin", "editor", "visitor" })
    public List<CostOutputMonth> findCostLatestPerMonthByAppId(@PathParam("appId") Long appId) {
        return costService.findCostLatestPerMonthByAppId(appId);
    }
}
