package org.acme.category.infra.database.entity;

public class CategoryODAParentEntityHelper {
    public static CategoryODAParentEntity entityFromId(Long id){
        CategoryODAParentEntity entity = new CategoryODAParentEntity();
        entity.id = id;
        return entity; 
    }
}