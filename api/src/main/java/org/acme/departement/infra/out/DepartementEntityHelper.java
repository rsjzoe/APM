package org.acme.departement.infra.out;


public class DepartementEntityHelper {
    public static DepartementEntity entityFromId(Long id) {
        DepartementEntity entity = new DepartementEntity();
        entity.id = id;
        return entity;
    }
}
