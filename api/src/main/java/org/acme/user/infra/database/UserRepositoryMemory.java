package org.acme.user.infra.database;

import java.util.List;

import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.input.UpdateUserInput;
import org.acme.user.domain.model.User;
import org.acme.user.domain.port.UserRepository;

public class UserRepositoryMemory implements UserRepository {
    private List<User> users;

    @Override
    public User save(CreateUserInput createUser) {
        User user = new User(1L, createUser.name, createUser.email, createUser.role);
        users.add(user);
        return user;
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public User update(Long id, UpdateUserInput data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
