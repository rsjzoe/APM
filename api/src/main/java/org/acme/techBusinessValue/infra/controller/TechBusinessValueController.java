package org.acme.techBusinessValue.infra.controller;

import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.in.TechBusinessValueRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;

@Path("techBusinessvalue")
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
        }
    }

}
