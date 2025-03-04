package org.acme.category.domain.input;

public class UpdateCategoryODAChild {
    private String name;
    private Long parentId;

    public UpdateCategoryODAChild() {
    }

    public UpdateCategoryODAChild(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
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
        return "UpdateCategoryODAChild{" +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}