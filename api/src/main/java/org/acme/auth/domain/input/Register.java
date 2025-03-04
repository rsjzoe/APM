package org.acme.auth.domain.input;

import org.acme.user.domain.Role;

public class Register {
    private String name;
    private String trigramme;
    private String departement;
    private Role role;

    public Register(String name, String trigramme, String departement, Role role) {
        this.name = name;
        this.trigramme = trigramme;
        this.departement = departement;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", trigramme='" + trigramme + '\'' +
                ", departement='" + departement + '\'' +
                ", role=" + role +
                '}';
    }
}
