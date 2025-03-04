package org.acme.auth.adapter.in;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.auth.domain.port.in.AuthRest;
import org.acme.roleGuard.RoleAllowedCustom;
import org.acme.user.domain.UserOutput;
import org.jboss.resteasy.reactive.RestQuery;

import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ServerErrorException;

@Path("/auth")
public class AuthController implements AuthRest {
    @Inject
    AuthService authService;

    @POST
    @Override
    @Path("/login")
    @PermitAll
    public Token login(Login login) {
        try {
            return authService.login(login);
        } catch (LoginException e) {
            throw new UnauthorizedException();
        }
    }

    @Override
    @POST
    @Path("/register")
    @Authenticated
    @RoleAllowedCustom({"admin"})
    public UserOutput register(Register register) {
        try {
            return authService.register(register);
        } catch (UserExistedException e) {
            throw new BadRequestException("user existed");
        } catch (UserCreatedException e) {
            throw new BadRequestException("user created");
        }
    }

    @Override
    @GET
    @PermitAll
    @Path("/refreshToken")
    public Token refreshToken(@RestQuery String refreshToken) {
        try {
            return authService.refreshToken(refreshToken);
        } catch (LoginException e) {
            throw new UnauthorizedException();
        } catch (Exception e) {
            throw new ServerErrorException(500);
        }
    }

}
