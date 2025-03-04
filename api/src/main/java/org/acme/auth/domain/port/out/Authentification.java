package org.acme.auth.domain.port.out;


import org.acme.auth.domain.Token;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.user.domain.UserOutput;

public interface Authentification {
    Token login(Login login) throws LoginException;

    Token refreshToken(String refreshToken) throws LoginException,Exception;

    UserOutput register(Register register) throws UserExistedException, UserCreatedException;
}
