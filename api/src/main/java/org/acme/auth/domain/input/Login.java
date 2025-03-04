package org.acme.auth.domain.input;

public class Login {
    private String trigramme;
    private String password;

    public Login() {
    }

    public Login(String trigramme, String password) {
        this.trigramme = trigramme;
        this.password = password;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
