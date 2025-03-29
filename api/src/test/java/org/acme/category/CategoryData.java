package org.acme.category;

import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

@ApplicationScoped
@Getter
public class CategoryData {
    private CategoryODAParentEntity categoryParent;
    private CategoryODAChildEntity categoryChild;

    public void setup() {
        categoryParent = new CategoryODAParentEntity("ParentCategory", "#FFFFFF");
        categoryParent.persistAndFlush();

        categoryChild = new CategoryODAChildEntity("ChildCategory");
        categoryChild.setCategoryODAParentEntity(categoryParent);
        categoryChild.persistAndFlush();
    }

    public CreateCategoryODAChild createCategoryODAChild() {
        return new CreateCategoryODAChild("child", categoryParent.id);
    }

    public UpdateCategoryODAChild updateCategoryODAChild() {
        return new UpdateCategoryODAChild("update child", categoryParent.id);
    }

    public CreateCategoryODAParent createCategoryODAParent() {
        return new CreateCategoryODAParent("parent", "#000");
    }

    public UpdateCategoryODAParent updateCategoryODAParent() {
        return new UpdateCategoryODAParent("update parent", "#000");
    }
}
