package org.acme.classe.domain.input;

public class CreateClasseInput {
    private String name;
    private String description;

    public CreateClasseInput() {
    }

    public CreateClasseInput(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
