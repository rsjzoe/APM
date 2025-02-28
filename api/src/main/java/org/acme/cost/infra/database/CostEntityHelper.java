package org.acme.cost.infra.database;

public class CostEntityHelper {
    public static CostEntity entityFromId(Long id) {
        CostEntity entity = new CostEntity();
        entity.id = id;
        return entity;
    }   
}
