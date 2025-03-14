package org.acme.category.domain.port.out;

import java.util.List;

import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;

public interface CategoryODAChildRepository {
    CategoryODAChildOutput save(CreateCategoryODAChild categoryChild);

    CategoryODAChildOutput findById(Long id) throws CategoryODAChildNotFoundException;

    CategoryODAChildOutput deleteById(Long id) throws CategoryODAChildNotFoundException;

    CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild)
            throws CategoryODAChildNotFoundException;

    List<CategoryODAChildOutput> findAll();
}