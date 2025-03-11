package org.acme.category.adapter.out.Entity;

public class CategoryODAParentEntityHelper {
    public static CategoryODAParentEntity entityFromId(Long id){
        CategoryODAParentEntity entity = new CategoryODAParentEntity();
        entity.id = id;
        return entity; 
    }
}