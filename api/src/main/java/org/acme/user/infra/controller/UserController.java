package org.acme.user.infra.controller;

import java.util.List;

import org.acme.user.app.UserService;
import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.exception.WrongPasswordException;
import org.acme.user.domain.input.ChangePassword;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.port.in.UserRest;

import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/user")
@Authenticated
public class UserController implements UserRest {

    @Inject
    UserService userService;

    @Override
    @Path("/me")
    @GET
    public UserOutput me(@HeaderParam("Authorization") String authHeader) {
        String token = authHeader.substring("Bearer ".length());
        try {
            return userService.me(token);
        } catch (VerificationTokenException e) {
            System.out.println(e.getMessage());
            throw new UnauthorizedException();
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @GET
    public List<UserOutput> findAllUser() {
        return userService.findAllUsers();
    }

    @Override
    @DELETE
    @Path("/{trigramme}")
    public UserOutput deleteByTrigramme(@PathParam("trigramme") String trigramme) {
        try {
            return userService.deleteUserByTrigramme(trigramme);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @PUT
    @Path("/{trigramme}")
    public UserOutput updateByTrigramme(@PathParam("trigramme") String trigramme, UpdateUser userUpdate) {
        try {
            return userService.updateUserByTrigramme(trigramme, userUpdate);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @PUT
    @Path("/change-password/{trigramme}")
    public UserOutput changePassword(@PathParam("trigramme") String trigramme, ChangePassword changePassword) {
        try {
            return userService.changePassword(trigramme, changePassword);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        } catch (WrongPasswordException e) {
            throw new UnauthorizedException();
        }
    }
}
