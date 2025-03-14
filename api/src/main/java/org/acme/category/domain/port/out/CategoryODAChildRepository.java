package org.acme.category.domain.port.out;

import java.util.List;

import org.acme.category.domain.exception.CategoryChildNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;

public interface CategoryODAChildRepository {
    CategoryODAChildOutput save(CreateCategoryODAChild categoryChild);

    CategoryODAChildOutput findById(Long id) throws CategoryChildNotFoundException;

    CategoryODAChildOutput deleteById(Long id) throws CategoryChildNotFoundException;

    CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild)
            throws CategoryChildNotFoundException;

    List<CategoryODAChildOutput> findAll();
}