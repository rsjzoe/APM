package org.acme.applicationAPM.infra.database;

public class ApplicationEntityHelper {
        public static ApplicationEntity entityFromId(Long id) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.id = id;
        return entity;
    }
}
