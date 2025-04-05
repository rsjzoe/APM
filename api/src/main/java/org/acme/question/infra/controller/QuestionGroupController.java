package org.acme.question.infra.controller;

import java.util.List;

import org.acme.question.app.service.QuestionGroupService;
import org.acme.question.domain.exception.QuestionGroupNotFoundException;
import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;
import org.acme.question.domain.port.in.QuestionGroupRest;
import org.acme.roleGuard.RoleAllowedCustom;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/question-group")
@Authenticated
public class QuestionGroupController implements QuestionGroupRest {

    @Inject
    QuestionGroupService questionGroupService;

    @POST
    @Override
    @RoleAllowedCustom({ "admin" })
    public QuestionGroup create(CreateQuestionGroup questionGroup) {
        return questionGroupService.save(questionGroup);
    }

    @PUT
    @Path("/{id}")
    @Override
    @RoleAllowedCustom({ "admin" })
    public QuestionGroup update(@PathParam("id") Long id, UpdateQuestionGroup questionGroup) {
        try {
            return questionGroupService.update(id, questionGroup);
        } catch (QuestionGroupNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GET
    @Path("/{id}")
    @Override
    public QuestionGroup findById(@PathParam("id") Long id) {
        try {
            return questionGroupService.findById(id);
        } catch (QuestionGroupNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Override
    @RoleAllowedCustom({ "admin" })
    public QuestionGroup deleteById(@PathParam("id") Long id) {
        try {
            return questionGroupService.deleteById(id);
        } catch (QuestionGroupNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GET
    @Override
    public List<QuestionGroup> findAll() {
        return questionGroupService.findAll();
    }

}
