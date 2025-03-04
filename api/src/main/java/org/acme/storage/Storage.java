package org.acme.storage;

import java.io.IOException;


public interface Storage {
    StorageFile save(FileInput fileInput) throws FileNotFound, IOException;

    StorageFile get(String filename) throws FileNotFound;

    byte[] getBytes(String filename) throws FileNotFound, IOException;

    void delete(String filename) throws FileNotFound;
}
