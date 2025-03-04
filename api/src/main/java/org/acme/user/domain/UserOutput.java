package org.acme.user.domain;

public class UserOutput {
    private String name;
    private String trigramme;
    private Role role;
    private String departement;

    public UserOutput(String name, String trigramme, String departement, Role role) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
