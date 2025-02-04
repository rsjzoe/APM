package org.acme.category.infra.out;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.category.domain.Category;
import org.acme.category.domain.port.out.CategoryRepository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryEntityRepository implements CategoryRepository {

    @Transactional
    @Override
    public List<Category> listAll() {
        List<CategoryEntity> category = CategoryEntity.listAll();
        return category.stream()
                .map(Category -> Category.toCategory())
                .collect(Collectors.toList());
    }

    @Override
    public Category  findById(int id) {
        CategoryEntity category = CategoryEntity.findById(id);
        return category.toCategory();
    }

}
