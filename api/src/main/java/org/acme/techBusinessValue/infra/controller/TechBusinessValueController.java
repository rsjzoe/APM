package org.acme.techBusinessValue.infra.controller;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.in.TechBusinessValueRest;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;

@Path("techBusinessvalue")
@Authenticated
public class TechBusinessValueController implements TechBusinessValueRest {
    @Inject
    TechBusinessValueService techBusinessValueService;

    @Override
    @Transactional
    @GET
    @Path("/{appId}")
    public List<TechBusinessValueOutput> findTechBusinessValueByAppId(@PathParam("appId") Long appId) {
        return techBusinessValueService.findTechBusinessValueOutputByAppId(appId);
    }

    @Override
    @Transactional
    @POST
    public TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue) {
        try {
            return techBusinessValueService.createTechBusinessValueOutput(techBusinessValue);
        } catch (InvalidTechBusinessValueException e) {
            throw new BadRequestException(e);
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @Transactional
    @GET
    @Path("/latest-per-month/{appId}")
    public List<TechBusinessValueMonth> findTechBusinessValueLatestPerMonthByAppId(@PathParam("appId") Long appId) {
        return techBusinessValueService.findTechBusinessValueLatestPerMonthByAppId(appId);
    }

}
