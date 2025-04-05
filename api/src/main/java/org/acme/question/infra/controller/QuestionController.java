package org.acme.question.infra.controller;

import java.util.List;

import org.acme.question.app.service.QuestionService;
import org.acme.question.domain.exception.QuestionNotFoundException;
import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.port.in.QuestionRest;
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

@Path("/question")
@Authenticated
public class QuestionController implements QuestionRest {
    @Inject
    QuestionService questionService;

    @POST
    @Override
    @RoleAllowedCustom({ "admin" })
    public Question save(CreateQuestion question) {
        return questionService.save(question);
    }

    @PUT
    @Path("/{id}")
    @Override
    @RoleAllowedCustom({ "admin" })
    public Question update(@PathParam("id") Long id, UpdateQuestion question) {
        try {
            return questionService.update(id, question);
        } catch (QuestionNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GET
    @Path("/{id}")
    @Override
    public Question findById(@PathParam("id") Long id) {
        try {
            return questionService.findById(id);
        } catch (QuestionNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Override
    @RoleAllowedCustom({ "admin" })
    public Question deleteById(@PathParam("id") Long id) {
        try {
            return questionService.deleteById(id);
        } catch (QuestionNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    public List<Question> findAll() {
        return questionService.findAll();
    }
}
