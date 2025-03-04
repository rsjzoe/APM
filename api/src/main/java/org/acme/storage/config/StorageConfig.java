package org.acme.storage.config;

import org.acme.storage.LocalStorage;
import org.acme.storage.Storage;

import jakarta.enterprise.inject.Produces;

public class StorageConfig {
    @Produces
    public Storage storage(){
        return new LocalStorage();
    }
}
