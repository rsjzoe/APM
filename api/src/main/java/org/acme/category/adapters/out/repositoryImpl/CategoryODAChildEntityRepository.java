package org.acme.category.adapters.out.repositoryImpl;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.ports.out.CategoryODAChildRepository;
import  org.acme.category.adapters.out.Entity.CategoryODAChildEntity;
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
        public CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryParent) {
            CategoryODAChildEntity entity = CategoryODAChildEntity.findById(id);
            entity.update(categoryParent);
            entity.persist();
            return entity.toCategoryODAChildOutput();
        }
    
    }

