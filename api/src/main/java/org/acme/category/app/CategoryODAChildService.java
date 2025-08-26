package org.acme.category.app;

import java.util.List;

import org.acme.SocketIOServerProvider;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.domain.port.out.CategoryODAChildRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAChildService {

    private CategoryODAChildRepository categoryODAChildRepository;

    @Inject
    SocketIOServerProvider socketio;

    public CategoryODAChildService(CategoryODAChildRepository categoryODAChildRepository) {
        this.categoryODAChildRepository = categoryODAChildRepository;
    }

    @Transactional
    public CategoryODAChildOutput findById(Long id) throws CategoryODAChildNotFoundException {
        return categoryODAChildRepository.findById(id);
    }

    @Transactional
    public CategoryODAChildOutput save(CreateCategoryODAChild categoryChild) {
        var created = categoryODAChildRepository.save(categoryChild);
        socketio.sendEvent("refetch_category");
        return created;
    }

    @Transactional
    public CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild)
            throws CategoryODAChildNotFoundException {
        var updated = categoryODAChildRepository.updateById(id, categoryChild);
        socketio.sendEvent("refetch_category");
        return updated;
    }

    @Transactional
    public CategoryODAChildOutput deleteById(Long id) throws CategoryODAChildNotFoundException {
        var deleted = categoryODAChildRepository.deleteById(id);
        socketio.sendEvent("refetch_category");
        return deleted;
    }

    public List<CategoryODAChildOutput> findAll() {
        return categoryODAChildRepository.findAll();
    }

}