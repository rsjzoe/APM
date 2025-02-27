package org.acme.application.infra.database;

public class ApplicationEntityHelper {
        public static ApplicationEntity entityFromId(Long id) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.id = id;
        return entity;
    }
}
