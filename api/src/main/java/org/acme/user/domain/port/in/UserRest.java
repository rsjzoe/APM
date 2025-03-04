package org.acme.user.domain.port.in;

import java.util.List;

import org.acme.user.domain.UserOutput;

public interface UserRest {
    UserOutput  me(String authHeader);

    List<UserOutput> findAllUser();

    UserOutput deleteByTrigramme(String trigramme);

    UserOutput updateByTrigramme(String trigramme, UserOutput userUpdate);
} 
