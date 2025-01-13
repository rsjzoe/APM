package org.acme.user.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.input.UpdateUserInput;
import org.acme.user.domain.model.User;
import org.acme.user.domain.port.UserRepository;

public class UserRepositoryDatabase implements UserRepository {

    @Override
    public User save(CreateUserInput user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity.persist();
        return userEntity.toUser();
    }

    @Override
    public List<User> listAll() {
        // tandrema tsara ilay type de retour eto User
        // saingy ny any anaty database UserEntity, donc mila transformena
        // no-creena ao anaty UserEntity ilay toUser() io
        return UserEntity.listAll().stream()
                .map(entity -> ((UserEntity) entity).toUser())
                .collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = UserEntity.find("email", email).firstResult();
        return userEntity.toUser();
    }

    @Override
    public User update(Long id, UpdateUserInput data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
