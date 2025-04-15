package org.acme.user;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.role.RoleData;
import org.acme.user.app.UserService;
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

    @Inject
    RoleData roleData;

    private UserOutput userOutput;
    private UserOutput userAdmin;
    private UserOutput userReadAppOnly;
    private Token userAdminToken;
    private Token userReadAppOnlyToken;

    public void setup() {
        roleData.setup();
        try {
            userOutput = authService.register(
                    new Register("userOutput", "userOutput", "DSI", roleData.getApmsuperadmin().getRoleName()));
        } catch (UserExistedException | UserCreatedException e) {
        }
        try {
            userAdmin = authService
                    .register(new Register("userAdmin", "userAdmin", "DSI", roleData.getApmsuperadmin().getRoleName()));
        } catch (UserExistedException | UserCreatedException e) {
        }
        try {
            userReadAppOnly = authService.register(
                    new Register("userReadAppOnly", "userReadAppOnly", "DSI", roleData.getReadAppOnly().getRoleName()));
        } catch (UserExistedException | UserCreatedException e) {
        }
        try {
            userAdminToken = authService.login(new Login(userAdmin.getTrigramme(), "0000"));
        } catch (LoginException e) {
        }
        try {
            userReadAppOnlyToken = authService.login(new Login(userReadAppOnly.getTrigramme(), "0000"));
        } catch (LoginException e) {
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
        try {
            userService.deleteUserByTrigramme(userReadAppOnly.getTrigramme());
        } catch (UserNotFoundException e) {
        }
        roleData.clear();
    }
}
