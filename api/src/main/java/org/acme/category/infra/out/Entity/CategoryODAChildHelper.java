package org.acme.category.infra.out.Entity;

public class CategoryODAChildHelper {
    public static CategoryODAChildEntity entityFromId(Long id){
        CategoryODAChildEntity entity = new CategoryODAChildEntity();
        entity.id = id;
        return entity;
    }
}
