package org.acme.application.domain.exception;

public class InvalidApplicationException extends Exception {
    public InvalidApplicationException(String message) {
        super(message);
    }

    public InvalidApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
