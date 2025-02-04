package org.acme.category.app;

import java.util.List;

import org.acme.category.domain.Category;
import org.acme.category.domain.port.out.CategoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional

    public List<Category> listAll() {
        return categoryRepository.listAll();
    }

    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

}