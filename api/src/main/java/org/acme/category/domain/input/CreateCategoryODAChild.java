package org.acme.category.domain.input;

public class CreateCategoryODAChild {
    private String name;
    private Long parentId;

    public CreateCategoryODAChild() {
    }

    public CreateCategoryODAChild(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CreateCategoryODAChild{" +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}