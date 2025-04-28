package org.acme.role.domain.exception;

public class RoleActif extends Exception {
    public RoleActif(String message) {
        super(message);
    }

    public RoleActif() {
        super("Role is already active");
    }

}
