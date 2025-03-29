package org.acme.user.app;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.port.out.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput me(String token) throws VerificationTokenException, UserNotFoundException {
        return userRepository.me(token);
    }

    public List<UserOutput> findAllUsers() {
        return userRepository.findAllUser();
    }

    public UserOutput deleteUserByTrigramme(String trigramme) throws UserNotFoundException {
        return userRepository.deleteByTrigramme(trigramme);
    }

    public UserOutput updateUserByTrigramme(String trigramme, UpdateUser userUpdate) throws UserNotFoundException {
        return userRepository.updateByTrigramme(trigramme, userUpdate);
    }

}
