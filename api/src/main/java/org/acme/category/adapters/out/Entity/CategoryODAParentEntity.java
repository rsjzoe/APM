package org.acme.category.adapters.out.Entity;

import java.util.ArrayList;
import java.util.List;

import org.acme.category.domain.CategoryODAChild;
import org.acme.category.domain.CategoryODAParent;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.domain.output.CategoryODAParentOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class CategoryODAParentEntity extends PanacheEntity {
    private String name;
    private String bgColor;
    private boolean isDelete;

    @OneToMany(mappedBy = "categoryODAParentEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CategoryODAChildEntity> categoryODAChildEntities;

    public CategoryODAParentEntity() {
    }

    public CategoryODAParentEntity(String name, String bgColor) {
        this.name = name;
        this.bgColor = bgColor;
    }

    public CategoryODAParentEntity(CreateCategoryODAParent data) {
        this.name = data.getName();
        this.bgColor = data.getBgColor();
        this.isDelete = false;
    }

    public List<CategoryODAChildOutput> categoryODAChilds() {
        if (categoryODAChildEntities == null)
            return new ArrayList<>();
        return categoryODAChildEntities.stream().map(CategoryODAChildEntity::toCategoryODAChildOutput).toList();
    }

    public CategoryODAParentOutput toCategoryODAParentOutput() {
        return new CategoryODAParentOutput(this.id, this.name, this.bgColor, isDelete, categoryODAChilds());
    }

    public CategoryODAParent toCategoryODAParent() {
        return new CategoryODAParent(this.id, this.name, this.bgColor, isDelete,
                categoryODAChildEntities.stream()
                        .map(el -> new CategoryODAChild(el.id, el.getName(), el.isDelete(), null)).toList());
    }

    public void update(UpdateCategoryODAParent data) {
        this.name = data.getName();
        this.bgColor = data.getBgColor();
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public List<CategoryODAChildEntity> getCategoryODAChildEntities() {
        return categoryODAChildEntities != null ? categoryODAChildEntities : new ArrayList<>();
    }

    public void setCategoryODAChildEntities(List<CategoryODAChildEntity> categoryODAChildEntities) {
        this.categoryODAChildEntities = categoryODAChildEntities;
    }

    @Override
    public String toString() {
        return "CategoryODAEntity{" +
                "name='" + name + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", isDelete=" + isDelete +
                ", categoryODAChildEntities=" + categoryODAChildEntities +
                '}';
    }
}