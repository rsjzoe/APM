package org.acme.user.app;

import java.util.List;

import org.acme.user.domain.UserOutput;
import org.acme.user.domain.exception.VerificationTokenException;
import org.acme.user.domain.port.out.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput me(String token) throws VerificationTokenException{
        return userRepository.me(token);
    }

    public List<UserOutput> findAllUsers(){
        return userRepository.findAllUser();
    }

    public UserOutput deleteUserByTrigramme(String trigramme) {
        return userRepository.deleteByTrigramme(trigramme);
    }

    public UserOutput updateUserByTrigramme(String trigramme, UserOutput userUpdate) {
        return userRepository.updateByTrigramme(trigramme, userUpdate);
    }

}
