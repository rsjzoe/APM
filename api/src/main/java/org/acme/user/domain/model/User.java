package org.acme.user.domain.model;

public class User {
    protected Long id;
    protected String name;
    protected String email;
    protected Role role;

    public User(Long id, String name, String email, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ...
}
