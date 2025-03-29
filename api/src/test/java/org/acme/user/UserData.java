package org.acme.user;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Register;
import org.acme.user.domain.Role;
import org.acme.user.domain.UserOutput;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ApplicationScoped
@Getter
public class UserData {
    @Inject
    AuthService authService;

    private UserOutput userOutput;

    public void setup() {
        try {
            userOutput = authService.register(new Register("useradmin", "useradmin", "DSI", Role.admin));
        } catch (UserExistedException | UserCreatedException e) {
                e.printStackTrace();
        }
    }
}
