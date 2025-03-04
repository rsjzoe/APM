package org.acme.category.app;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.ports.CategoryODAParentService;
import org.acme.category.ports.out.CategoryODAParentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAParentUseCase implements CategoryODAParentService {
    CategoryODAParentRepository repository;

    public CategoryODAParentUseCase(CategoryODAParentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CategoryODAParentOutput save(CreateCategoryODAParent categoryParent) {
        return repository.save(categoryParent);
    }

    @Override
    public List<CategoryODAParentOutput> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public CategoryODAParentOutput findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public CategoryODAParentOutput deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent) {
        return repository.updateById(id, categoryParent);
    }

}