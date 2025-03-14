package org.acme.category.domain.port.out;

import java.util.List;

import org.acme.category.domain.exception.CategoryParentNotFoundException;
import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;

public interface CategoryODAParentRepository {
    CategoryODAParentOutput save(CreateCategoryODAParent categoryParent);

    List<CategoryODAParentOutput> findAll();

    CategoryODAParentOutput findById(Long id) throws CategoryParentNotFoundException;

    CategoryODAParentOutput deleteById(Long id) throws CategoryParentNotFoundException;

    CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent)
            throws CategoryParentNotFoundException;
}