package org.acme.category.adapter.out.repositoryImpl;

import java.util.List;

import org.acme.category.adapter.out.Entity.CategoryODAChildEntity;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.domain.port.out.CategoryODAChildRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryODAChildEntityRepository implements CategoryODAChildRepository {

    @Override
    public CategoryODAChildOutput save(CreateCategoryODAChild categoryChild) {
        CategoryODAChildEntity entity = new CategoryODAChildEntity(categoryChild);
        entity.persist();
        return entity.toCategoryODAChildOutput();
    }

    @Override
    public List<CategoryODAChildOutput> findAll() {
        List<CategoryODAChildEntity> entities = CategoryODAChildEntity.list("isDelete = ?1", false);
        return entities.stream().map(CategoryODAChildEntity::toCategoryODAChildOutput).toList();
    }

    @Override
    public CategoryODAChildOutput findById(Long id) {
        CategoryODAChildEntity entity = CategoryODAChildEntity.findById(id);
        return entity.toCategoryODAChildOutput();
    }

    @Override
    public CategoryODAChildOutput deleteById(Long id) {
        CategoryODAChildEntity entity = CategoryODAChildEntity.findById(id);

        entity.setDelete(true);
        entity.persist();
        return entity.toCategoryODAChildOutput();
    }

    @Override
    public CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild) {
        CategoryODAChildEntity entity = CategoryODAChildEntity.findById(id);
        entity.update(categoryChild);
        entity.persist();
        return entity.toCategoryODAChildOutput();
    }

}
