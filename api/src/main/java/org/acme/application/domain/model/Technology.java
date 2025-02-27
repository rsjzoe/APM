package org.acme.application.domain.model;

public class Technology {
    private String name;

    public Technology() {
    }

    public Technology(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Technology{" +
                "name='" + name + '\'' +
                '}';
    }
}
