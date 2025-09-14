package org.acme.user.app;

import java.util.List;

import org.acme.SocketIOServerProvider;
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
import org.acme.user.domain.query.UserQuery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;
    @Inject
    AuthService authService;

    @Inject
    SocketIOServerProvider socketio;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput me(String token) throws VerificationTokenException, UserNotFoundException {
        return userRepository.me(token);
    }

    public List<UserOutput> findAllUsers(UserQuery query) {
        return userRepository.findAllUser(query)
                .stream()
                .filter(user -> !("superadmin".equalsIgnoreCase(user.getTrigramme())
                        || "superadminapm".equalsIgnoreCase(user.getTrigramme())))
                .toList();
    }

    public UserOutput deleteUserByTrigramme(String trigramme) throws UserNotFoundException {
        if (trigramme.equalsIgnoreCase("superadmin") || trigramme.equalsIgnoreCase("superadminapm")) {
            throw new UserNotFoundException("Cannot");
        }
        var deleted = userRepository.deleteByTrigramme(trigramme);
        socketio.sendEvent("refetch_users");
        return deleted;
    }

    public UserOutput updateUserByTrigramme(String trigramme, UpdateUser userUpdate) throws UserNotFoundException {
        if (trigramme.equalsIgnoreCase("superadmin") || trigramme.equalsIgnoreCase("superadminapm")) {
            throw new UserNotFoundException("Cannot");
        }
        var updated = userRepository.updateByTrigramme(trigramme, userUpdate);
        socketio.sendEvent("refetch_users");
        return updated;
    }

    public UserOutput changePassword(String trigramme, ChangePassword password)
            throws UserNotFoundException, WrongPasswordException {
        try {
            authService.login(new Login(trigramme, password.getOldPassword()));
            var updated = userRepository.changePassword(trigramme, password);
            socketio.sendEvent("refetch_users");
            return updated;
        } catch (LoginException e) {
            throw new WrongPasswordException("Wrong password");
        }
    }

}
