package org.acme.category.infra.database.entity;

public class CategoryODAChildHelper {
    public static CategoryODAChildEntity entityFromId(Long id){
        CategoryODAChildEntity entity = new CategoryODAChildEntity();
        entity.id = id;
        return entity;
    }
}
