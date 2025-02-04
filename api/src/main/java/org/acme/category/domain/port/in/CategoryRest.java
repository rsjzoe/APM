package org.acme.category.domain.port.in;

import java.util.List;

import org.acme.category.domain.Category;


public interface CategoryRest {
    List<Category> listAll();

    Category findById(int id);

}
