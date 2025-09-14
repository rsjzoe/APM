package org.acme.user.domain.port.in;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.input.ChangePassword;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.query.UserQuery;

public interface UserRest {
    UserOutput me(String authHeader);

    List<UserOutput> findAllUser(UserQuery query);

    UserOutput deleteByTrigramme(String trigramme);

    UserOutput updateByTrigramme(String trigramme, UpdateUser userUpdate);

    UserOutput changePassword(String trigramme, ChangePassword changePassword);
}
