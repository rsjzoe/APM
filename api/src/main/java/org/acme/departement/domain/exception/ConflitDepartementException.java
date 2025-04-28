package org.acme.departement.domain.exception;

public class ConflitDepartementException extends Exception {
    public ConflitDepartementException() {
        super("Le département existe déjà");
    }

    public ConflitDepartementException(String message) {
        super(message);
    }

    public ConflitDepartementException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflitDepartementException(Throwable cause) {
        super(cause);
    }

}
