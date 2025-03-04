package org.acme.category.ports.out;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;

public interface CategoryODAChildRepository {
    CategoryODAChildOutput save(CreateCategoryODAChild categoryChild);

    CategoryODAChildOutput findById(Long id);

    CategoryODAChildOutput deleteById(Long id);

    CategoryODAChildOutput updateById(Long id, UpdateCategoryODAChild categoryChild);

    List<CategoryODAChildOutput> findAll();
}