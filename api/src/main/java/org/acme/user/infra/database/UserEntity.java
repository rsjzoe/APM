package org.acme.user.infra.database;

import org.acme.user.domain.input.CreateUserInput;
import org.acme.user.domain.model.Role;
import org.acme.user.domain.model.User;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class UserEntity extends PanacheEntity {
    public String name;
    public String email;
    public Role role;

    public UserEntity() {

    }

    public UserEntity(String name, String email, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public UserEntity(CreateUserInput user) {
        this(user.name, user.email, user.role);
    }

    public User toUser() {
        return new User(id, name, email, role);
    }
}
