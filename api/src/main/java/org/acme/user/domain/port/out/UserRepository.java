package org.acme.user.domain.port.out;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.input.ChangePassword;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.query.UserQuery;

public interface UserRepository {
    UserOutput me(String token) throws VerificationTokenException, UserNotFoundException;

    List<UserOutput> findAllUser(UserQuery query);

    UserOutput deleteByTrigramme(String trigramme) throws UserNotFoundException;

    UserOutput updateByTrigramme(String trigramme, UpdateUser userUpdate) throws UserNotFoundException;

    UserOutput changePassword(String trigramme, ChangePassword changePassword)
            throws UserNotFoundException;
}
