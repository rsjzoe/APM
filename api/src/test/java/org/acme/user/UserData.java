package org.acme.user;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.user.app.UserService;
import org.acme.user.domain.Role;
import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ApplicationScoped
@Getter
public class UserData {
    @Inject
    AuthService authService;

    @Inject
    UserService userService;

    private UserOutput userOutput;
    private UserOutput userAdmin;
    private Token userAdminToken;

    public void setup() {
        try {
            userOutput = authService.register(new Register("userOutput", "userOutput", "DSI", Role.admin));
            userAdmin = authService.register(new Register("userAdmin", "userAdmin", "DSI", Role.admin));
            userAdminToken = authService.login(new Login(userAdmin.getTrigramme(), "0000"));
        } catch (UserExistedException | UserCreatedException | LoginException e) {
        }
    }

    public void clear() {
        try {
            userService.deleteUserByTrigramme(userOutput.getTrigramme());
        } catch (UserNotFoundException e) {
        }
        try {
            userService.deleteUserByTrigramme(userAdmin.getTrigramme());
        } catch (UserNotFoundException e) {
        }
    }
}
