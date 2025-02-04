package org.acme.category.infra.out;

public class CategoryEntityHelper {
    public static CategoryEntity entityFromId(Long id) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.id = id;
        return categoryEntity;
    }
}
