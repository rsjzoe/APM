package org.acme.user.infra.controller;

import java.util.List;

import org.acme.user.app.services.UserService;
import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.input.UpdateUserInput;
import org.acme.user.domain.model.User;
import org.acme.user.domain.port.in.UserRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/users")
public class UserController implements UserRest {
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

    @Override
    public User add(CreateUserInput user) {
        // TODO Auto-generated method stub
        return userService.save(user);

    }

    @Override
    public User delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public User update(Long id, UpdateUserInput upUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<User> listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }
    
}
