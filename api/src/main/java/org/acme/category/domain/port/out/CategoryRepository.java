package org.acme.category.domain.port.out;

import java.util.List;

import org.acme.category.domain.Category;


public interface CategoryRepository {
        List<Category> listAll();

        Category findById(int id);
}
