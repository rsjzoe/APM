package org.acme.storage;

import java.nio.file.Path;

public class FileInput {
    private Path path;
    private String name;

    public FileInput(Path path, String name) {
        this.path = path;
        this.name = name;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileInput{" +
                "path=" + path +
                ", name='" + name + '\'' +
                '}';
    }}
