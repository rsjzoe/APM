package org.acme.user.app.usecase;

import org.acme.user.domain.model.User;
import org.acme.user.domain.port.EmailService;

import jakarta.enterprise.context.ApplicationScoped;

// afaka fafana fa juste oe exemple amle usecase mba ho azondri
@ApplicationScoped
public class CreateUser {
    private EmailService emailService;
    // private UserRepository userRepository;

    public User save(User user) {
        emailService.send(user);
        // userRepository.save(user);
        return user;
    }
}
