package org.acme.category.app;

import java.util.List;

import org.acme.category.domain.exception.CategoryParentNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.domain.port.out.CategoryODAParentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAParentService {
    CategoryODAParentRepository repository;

    public CategoryODAParentService(CategoryODAParentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CategoryODAParentOutput save(CreateCategoryODAParent categoryParent) {
        return repository.save(categoryParent);
    }

    public List<CategoryODAParentOutput> findAll() {
        return repository.findAll();
    }

    @Transactional
    public CategoryODAParentOutput findById(Long id) throws CategoryParentNotFoundException {
        return repository.findById(id);
    }

    @Transactional
    public CategoryODAParentOutput deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Transactional
    public CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent)
            throws CategoryParentNotFoundException {
        return repository.updateById(id, categoryParent);
    }

}