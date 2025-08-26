package org.acme.auth.app;

import org.acme.SocketIOServerProvider;
import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.auth.domain.port.out.Authentification;
import org.acme.user.domain.UserOutput;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {
    private final Authentification authentification;
    @Inject
    SocketIOServerProvider socketio;

    public AuthService(Authentification authentification) {
        this.authentification = authentification;
    }

    public Token login(Login login) throws LoginException {
        return authentification.login(login);
    }

    public UserOutput register(Register register) throws UserExistedException, UserCreatedException {
        if (register.getTrigramme().equalsIgnoreCase("superadmin")
                || register.getTrigramme().equalsIgnoreCase("superadminapm")) {
            throw new UserExistedException();
        }
        var newUser = authentification.register(register);
        socketio.sendEvent("refetch_users");
        return newUser;
    }

    public Token refreshToken(String token) throws LoginException, Exception {
        return authentification.refreshToken(token);
    }

}
