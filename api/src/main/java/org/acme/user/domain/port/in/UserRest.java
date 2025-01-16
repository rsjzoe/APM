package org.acme.user.domain.port.in;

import java.util.List;

import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.input.UpdateUserInput;
import org.acme.user.domain.model.User;

public interface UserRest {
    User add(CreateUserInput user);

    User delete(Long id);

    User update(Long id, UpdateUserInput upUser);

    List<User> listAll();
}
