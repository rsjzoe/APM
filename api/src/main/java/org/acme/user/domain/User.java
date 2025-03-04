package org.acme.user.domain;

public class User {
    private String name;
    private String trigramme;
    private String department;
    private Role role;
    private String password;

    public User(String name, String trigramme, String department,Role role, String password) {
        this.name = name;
        this.trigramme = trigramme;
        this.department = department;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public String getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public UserOutput toOutput() {
        return new UserOutput(name, trigramme, department, role);
    }
}
