package org.acme.category.app;

import java.util.List;

import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.domain.port.out.CategoryODAChildRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAChildService {

    private CategoryODAChildRepository categoryODAChildRepository;

    public CategoryODAChildService(CategoryODAChildRepository categoryODAChildRepository) {
        this.categoryODAChildRepository = categoryODAChildRepository;
    }

    @Transactional
    public CategoryODAChildOutput findById(Long id) throws CategoryODAChildNotFoundException {
        return categoryODAChildRepository.findById(id);
    }

    @Transactional
    public CategoryODAChildOutput save(CreateCategoryODAChild categoryChild) {
        return categoryODAChildRepository.save(categoryChild);
    }

    @Transactional
    public CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild)
            throws CategoryODAChildNotFoundException {
        return categoryODAChildRepository.updateById(id, categoryChild);
    }

    @Transactional
    public CategoryODAChildOutput deleteById(Long id) throws CategoryODAChildNotFoundException {
        return categoryODAChildRepository.deleteById(id);
    }

    public List<CategoryODAChildOutput> findAll() {
        return categoryODAChildRepository.findAll();
    }

}