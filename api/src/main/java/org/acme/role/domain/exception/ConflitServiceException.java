package org.acme.role.domain.exception;

public class ConflitServiceException extends Exception {
    public ConflitServiceException(String message) {
        super(message);
    }

    public ConflitServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflitServiceException(Throwable cause) {
        super(cause);
    }
    
}
