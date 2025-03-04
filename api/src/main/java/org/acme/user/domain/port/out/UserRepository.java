package org.acme.user.domain.port.out;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.VerificationTokenException;

public interface UserRepository {
    UserOutput me(String token) throws VerificationTokenException;

    List<UserOutput> findAllUser();

    UserOutput deleteByTrigramme(String trigramme);

    UserOutput updateByTrigramme(String trigramme, UserOutput userUpdate);

}
