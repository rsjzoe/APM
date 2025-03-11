package org.acme.category.domain.port.in;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;

public interface CategoryODAParentRest {
    CategoryODAParentOutput save(CreateCategoryODAParent categoryParent);

    List<CategoryODAParentOutput> findAll();

    CategoryODAParentOutput findById(Long id);

    CategoryODAParentOutput deleteById(Long id);

    CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent);
}