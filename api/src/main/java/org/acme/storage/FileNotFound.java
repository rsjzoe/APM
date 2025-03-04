package org.acme.storage;

public class FileNotFound extends Exception {
    public FileNotFound(String message) {
        super(message);
    }
}
