package org.acme.user.app;

import java.util.List;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.input.Login;
import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.exception.WrongPasswordException;
import org.acme.user.domain.input.ChangePassword;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.port.out.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;
    @Inject
    AuthService authService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput me(String token) throws VerificationTokenException, UserNotFoundException {
        return userRepository.me(token);
    }

    public List<UserOutput> findAllUsers() {
        return userRepository.findAllUser()
                .stream()
                .filter(user -> !("superadmin".equalsIgnoreCase(user.getTrigramme())
                        || "superadminapm".equalsIgnoreCase(user.getTrigramme())))
                .toList();
    }

    public UserOutput deleteUserByTrigramme(String trigramme) throws UserNotFoundException {
        if (trigramme.equalsIgnoreCase("superadmin") || trigramme.equalsIgnoreCase("superadminapm")) {
            throw new UserNotFoundException("Cannot");
        }
        return userRepository.deleteByTrigramme(trigramme);
    }

    public UserOutput updateUserByTrigramme(String trigramme, UpdateUser userUpdate) throws UserNotFoundException {
        if (trigramme.equalsIgnoreCase("superadmin") || trigramme.equalsIgnoreCase("superadminapm")) {
            throw new UserNotFoundException("Cannot");
        }
        return userRepository.updateByTrigramme(trigramme, userUpdate);
    }

    public UserOutput changePassword(String trigramme, ChangePassword password)
            throws UserNotFoundException, WrongPasswordException {
        try {
            authService.login(new Login(trigramme, password.getOldPassword()));
            return userRepository.changePassword(trigramme, password);
        } catch (LoginException e) {
            throw new WrongPasswordException("Wrong password");
        }
    }

}
