package org.acme.user.domain.port.out;

import java.util.List;

import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.input.UpdateUserInput;
import org.acme.user.domain.model.User;

public interface UserRepository {
    User save(CreateUserInput user);

    List<User> listAll();

    User findByEmail(String email);

    User update(Long id, UpdateUserInput data);
}
