package org.acme.user.app.services;

import java.util.List;

// import org.acme.user.app.usecase.CreateUser;
import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.model.User;
import org.acme.user.domain.port.out.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

// manambatra ny logique metier anao iaby
// ex : rraha icree user de mila savena , andedfasana mail...
@ApplicationScoped
public class UserService {
    // private CreateUser createUser;
    @Inject
    UserRepository userRepository;

    // @Inject
    // EmailService emailService;

    public List<User> findAll() {
        return userRepository.listAll();
    }

    public User save(CreateUserInput user) {
        // afaka fafana fa juste oe exemple amle usecase mba ho azondri
        // createUser.save(user)
        // sendMail(user)
        // emailService.send(user);
        return userRepository.save(user);
    }
}
