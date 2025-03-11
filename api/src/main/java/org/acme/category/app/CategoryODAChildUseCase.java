package org.acme.category.app;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.domain.port.CategoryODAChildService;
import org.acme.category.domain.port.out.CategoryODAChildRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAChildUseCase implements CategoryODAChildService {
    
    private CategoryODAChildRepository categoryODAChildRepository;

    public CategoryODAChildUseCase(CategoryODAChildRepository categoryODAChildRepository) {
        this.categoryODAChildRepository = categoryODAChildRepository;
    }

    @Transactional
    public CategoryODAChildOutput findById(Long id) {
        return categoryODAChildRepository.findById(id);
    }

    @Transactional
    public CategoryODAChildOutput save(CreateCategoryODAChild categoryChild) {
        return categoryODAChildRepository.save(categoryChild);
    }

    @Transactional
    public CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild) {
        return categoryODAChildRepository.updateById(id, categoryChild);
    }

    @Transactional
    public CategoryODAChildOutput deleteById(Long id) {
        return categoryODAChildRepository.deleteById(id);
    }

    public List<CategoryODAChildOutput> findAll() {
        return categoryODAChildRepository.findAll();
    }

}