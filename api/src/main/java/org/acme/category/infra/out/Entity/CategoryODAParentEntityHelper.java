package org.acme.category.infra.out.Entity;

public class CategoryODAParentEntityHelper {
    public static CategoryODAParentEntity entityFromId(Long id){
        CategoryODAParentEntity entity = new CategoryODAParentEntity();
        entity.id = id;
        return entity; 
    }
}