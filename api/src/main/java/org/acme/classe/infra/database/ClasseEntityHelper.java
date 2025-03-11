package org.acme.classe.infra.database;

public class ClasseEntityHelper {
    public static ClasseEntity entityFromId(Long id) {
        ClasseEntity entity = new ClasseEntity();
        entity.id = id;
        return entity;
    }
}
