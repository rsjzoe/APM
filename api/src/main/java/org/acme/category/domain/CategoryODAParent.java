package org.acme.category.domain;

import java.util.List;

public class CategoryODAParent {
    private Long id;
    private String name;
    private String bgColor;
    private Boolean isDelete;
    private List<CategoryODAChild> childs;  

    public CategoryODAParent(Long id, String name, String bgColor, Boolean isDelete, List<CategoryODAChild> childs) {
        this.id = id;
        this.name = name;
        this.bgColor = bgColor;
        this.isDelete = isDelete;
        this.childs = childs;
    }

    public CategoryODAParent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<CategoryODAChild> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryODAChild> childs) {
        this.childs = childs;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "CategoryODAParent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", isDelete=" + isDelete +
                ", childs=" + childs +
                '}';
    }
}