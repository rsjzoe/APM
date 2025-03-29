package org.acme.user.domain.port.out;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;

public interface UserRepository {
    UserOutput me(String token) throws VerificationTokenException, UserNotFoundException;

    List<UserOutput> findAllUser();

    UserOutput deleteByTrigramme(String trigramme) throws UserNotFoundException;

    UserOutput updateByTrigramme(String trigramme, UserOutput userUpdate) throws UserNotFoundException;

}
