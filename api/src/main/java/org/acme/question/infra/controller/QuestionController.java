package org.acme.question.infra.controller;

import java.util.List;

import org.acme.question.app.service.QuestionService;
import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.port.in.QuestionRest;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/question")
public class QuestionController implements QuestionRest {
    @Inject
    QuestionService questionService;

    @POST
    @Override
    public Question save(CreateQuestion question) {
        return questionService.save(question);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Question update(@PathParam("id") Long id, UpdateQuestion question) {
        return questionService.update(id, question);
    }

    @GET
    @Path("/{id}")
    @Override
    public Question findById(@PathParam("id") Long id) {
        return questionService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Question deleteById(@PathParam("id") Long id) {
        return questionService.deleteById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionService.findAll();
    }
}
