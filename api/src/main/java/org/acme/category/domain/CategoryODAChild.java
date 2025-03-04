package org.acme.category.domain;

import org.acme.category.domain.output.CategoryODAChildOutput;

public class CategoryODAChild {
    private Long id;
    private String name;
    private Boolean isDelete;
    private CategoryODAParent parent;

    public CategoryODAChild() {
    }

    public CategoryODAChild(Long id, String name, Boolean isDelete, CategoryODAParent parent) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.parent = parent;
    }

    public CategoryODAChildOutput toOdaChildOutput() {
        return new CategoryODAChildOutput(id, name, isDelete, parent == null ? null : parent.getId(), parent == null ? null : parent.getName());
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryODAParent getParent() {
        return parent;
    }

    public void setParent(CategoryODAParent parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "CategoryODAChild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", parent=" + parent +
                '}';
    }
}