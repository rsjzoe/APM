package org.acme.user.config;

import org.acme.user.domain.port.UserRepository;
import org.acme.user.infra.database.UserRepositoryDatabase;

import jakarta.enterprise.inject.Produces;

public class UserConfig {
    @Produces
    public UserRepository userRepository() {
        return new UserRepositoryDatabase();
    }
}
