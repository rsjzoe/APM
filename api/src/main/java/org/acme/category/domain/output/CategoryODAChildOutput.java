package org.acme.category.domain.output;

public class CategoryODAChildOutput {
    private Long id;
    private String name;
    private Boolean isDelete;
    private Long parentId;
    private String parentName;

    public CategoryODAChildOutput() {
    }

    public CategoryODAChildOutput(Long id, String name, Boolean isDelete, Long parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.parentId = parentId;
        this.parentName = parentName;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return this.parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
