package org.acme.user.adapter.in;

import java.util.List;

import org.acme.user.app.UserService;
import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.port.in.UserRest;

import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
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
        return userService.deleteUserByTrigramme(trigramme);
    }

    @Override
    @PUT
    @Path("/{trigramme}")
    public UserOutput updateByTrigramme(@PathParam("trigramme") String trigramme, UserOutput userUpdate) {
        return userService.updateUserByTrigramme(trigramme, userUpdate);
    }
}
