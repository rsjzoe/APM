package org.acme.category.domain.output;

import java.util.List;

public class CategoryODAParentOutput {
    private Long id;
    private String name;
    private String bgColor;
    private Boolean isDelete;
    private List<CategoryODAChildOutput> childs;  

    public CategoryODAParentOutput(Long id, String name, String bgColor, Boolean isDelete, List<CategoryODAChildOutput> childs) {
        this.id = id;
        this.name = name;
        this.bgColor = bgColor;
        this.isDelete = isDelete;
        this.childs = childs;
    }

    public CategoryODAParentOutput() {
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

    public List<CategoryODAChildOutput> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryODAChildOutput> childs) {
        this.childs = childs;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

}
