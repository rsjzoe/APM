package org.acme.user.app.services;

import java.util.List;

// import org.acme.user.app.usecase.CreateUser;
import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.model.User;
import org.acme.user.domain.port.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    // private CreateUser createUser;
    @Inject
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.listAll();
    }

    public User save(CreateUserInput user) {
        // afaka fafana fa juste oe exemple amle usecase mba ho azondri
        // createUser.save(user);
        return userRepository.save(user);
    }
}
