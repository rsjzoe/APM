package org.acme.auth.domain.port.in;

import org.acme.auth.domain.Token;
import org.acme.auth.domain.input.Login;
import org.acme.auth.domain.input.Register;
import org.acme.user.domain.UserOutput;

public interface AuthRest {
    Token login(Login login);
    UserOutput register(Register register);
    Token refreshToken(String token);
}
