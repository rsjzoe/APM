package org.acme.category.app;

import java.util.List;

import org.acme.SocketIOServerProvider;
import org.acme.category.domain.exception.CategoryODAParentNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.domain.port.out.CategoryODAParentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryODAParentService {
    CategoryODAParentRepository repository;

    @Inject
    SocketIOServerProvider socketio;

    public CategoryODAParentService(CategoryODAParentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CategoryODAParentOutput save(CreateCategoryODAParent categoryParent) {
        var created = repository.save(categoryParent);
        socketio.sendEvent("refetch_category");
        return created;
    }

    public List<CategoryODAParentOutput> findAll() {
        return repository.findAll();
    }

    @Transactional
    public CategoryODAParentOutput findById(Long id) throws CategoryODAParentNotFoundException {
        return repository.findById(id);
    }

    @Transactional
    public CategoryODAParentOutput deleteById(Long id) throws CategoryODAParentNotFoundException {
        var deleted = repository.deleteById(id);
        socketio.sendEvent("refetch_category");
        return deleted;
    }

    @Transactional
    public CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent)
            throws CategoryODAParentNotFoundException {
        var updated = repository.updateById(id, categoryParent);
        socketio.sendEvent("refetch_category");
        return updated;
    }

}