package org.acme.user.domain.port.out;

import org.acme.user.domain.model.User;

// afaka fafana fa juste oe exemple amle usecase mba ho azondri
public interface EmailService {
    void send(User user);
}
