package org.acme.category.domain.input;

public class UpdateCategoryODAParent {
    private String name;
    private String bgColor;

    public UpdateCategoryODAParent() {
    }

    public UpdateCategoryODAParent(String name, String bgColor) {
        this.name = name;
        this.bgColor = bgColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public String toString() {
        return "UpdateCategoryODAParent{" +
                "name='" + name + '\'' +
                ", bgColor='" + bgColor + '\'' +
                '}';
    }
}