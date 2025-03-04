package org.acme.category.ports;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAParent;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.domain.output.CategoryODAParentOutput;

public interface CategoryODAParentService {
    CategoryODAParentOutput save(CreateCategoryODAParent categoryParent);

    List<CategoryODAParentOutput> findAll();

    CategoryODAParentOutput findById(Long id);

    CategoryODAParentOutput deleteById(Long id);

    CategoryODAParentOutput updateById(Long id, UpdateCategoryODAParent categoryParent);
}
