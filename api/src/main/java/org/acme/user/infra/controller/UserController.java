package org.acme.user.infra.controller;

import java.util.List;

import org.acme.user.app.services.UserService;
import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.model.User;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    public List<User> findAll() {
        return userService.findAll();
    }

    @POST
    @Transactional
    public User create(CreateUserInput userInput) {
        return userService.save(userInput);
    }
}
