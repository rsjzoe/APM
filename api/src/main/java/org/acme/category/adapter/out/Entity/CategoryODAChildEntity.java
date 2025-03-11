package org.acme.category.adapter.out.Entity;

import org.acme.category.domain.CategoryODAChild;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class CategoryODAChildEntity extends PanacheEntity {
    private String name;
    private boolean isDelete;

    @ManyToOne
    private CategoryODAParentEntity categoryODAParentEntity;

    public CategoryODAChildEntity() {
    }

    public CategoryODAChildEntity(String name) {
        this.name = name;
    }

    public CategoryODAChildEntity(CreateCategoryODAChild data) {
        this.name = data.getName();
        this.isDelete = false;
        this.categoryODAParentEntity = CategoryODAParentEntityHelper.entityFromId(data.getParentId());
        // CategoryODAParentEntity entity = new CategoryODAParentEntity();
        // entity.id = data.getParentId();
        // this.categoryODAParentEntity = entity;
    }

    public void update(UpdateCategoryODAChild data) {
        this.name = data.getName();
        this.categoryODAParentEntity = CategoryODAParentEntityHelper.entityFromId(data.getParentId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryODAChildOutput toCategoryODAChildOutput() {
        return new CategoryODAChildOutput(this.id, this.name, isDelete,
                categoryODAParentEntity == null ? null : categoryODAParentEntity.id,
                categoryODAParentEntity == null ? null : categoryODAParentEntity.getName());
    }

    public CategoryODAChild toCategoryODAChild() {
        return new CategoryODAChild(this.id, this.name, isDelete,
                categoryODAParentEntity == null ? null : categoryODAParentEntity.toCategoryODAParent());
    }

    public CategoryODAParentEntity getCategoryODAParentEntity() {
        return categoryODAParentEntity;
    }

    public void setCategoryODAParentEntity(CategoryODAParentEntity categoryODAParentEntity) {
        this.categoryODAParentEntity = categoryODAParentEntity;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "CategoryODAChildEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}