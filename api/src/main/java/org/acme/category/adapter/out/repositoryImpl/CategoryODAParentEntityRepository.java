package org.acme.category.adapter.out.repositoryImpl;

import java.util.List;

import org.acme.category.adapter.out.Entity.CategoryODAParentEntity;
import org.acme.category.domain.exception.CategoryParentNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.domain.port.out.CategoryODAParentRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryODAParentEntityRepository implements CategoryODAParentRepository {
    @Override
    public CategoryODAParentOutput save(CreateCategoryODAParent categoryParent) {
        CategoryODAParentEntity entity = new CategoryODAParentEntity(categoryParent);
        entity.persist();
        return entity.toCategoryODAParentOutput();
    }

    @Override
    public List<CategoryODAParentOutput> findAll() {
        List<CategoryODAParentEntity> entities = CategoryODAParentEntity.list("isDelete =? 1", false);
        return entities.stream()
                .peek(parent -> parent.setCategoryODAChildEntities(
                        parent.getCategoryODAChildEntities().stream()
                                .filter(child -> !child.isDelete())
                                .toList()))
                .map(CategoryODAParentEntity::toCategoryODAParentOutput)
                .toList();
    }

    @Override
    public CategoryODAParentOutput findById(Long id) throws CategoryParentNotFoundException {
        CategoryODAParentEntity entity = CategoryODAParentEntity.findById(id);
        if (entity == null)
            throw new CategoryParentNotFoundException();
        return entity.toCategoryODAParentOutput();
    }

    @Override
    public CategoryODAParentOutput deleteById(Long id) throws CategoryParentNotFoundException {
        CategoryODAParentEntity entity = CategoryODAParentEntity.findById(id);
        if (entity == null)
            throw new CategoryParentNotFoundException();
        entity.getCategoryODAChildEntities().forEach(child -> {
            child.setDelete(true);
            child.persist();
        });
        entity.setDelete(true);
        entity.persist();
        return entity.toCategoryODAParentOutput();
    }

    @Override
    public CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent)
            throws CategoryParentNotFoundException {
        CategoryODAParentEntity entity = CategoryODAParentEntity.findById(id);
        if (entity == null)
            throw new CategoryParentNotFoundException();
        entity.update(categoryParent);
        entity.persist();
        return entity.toCategoryODAParentOutput();
    }

}