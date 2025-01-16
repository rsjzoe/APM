package org.acme.user.app.services;

import org.acme.user.domain.model.User;
import org.acme.user.domain.port.out.EmailService;

import jakarta.enterprise.context.ApplicationScoped;

// afaka fafana fa juste oe exemple amle usecase mba ho azondri
@ApplicationScoped
public class SmptEmailService implements EmailService {

    @Override
    public void send(User user) {
        // Send mail
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

}
