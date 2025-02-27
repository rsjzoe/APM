package org.acme.techBusinessValue;

public class TechBusinessValueEntityHelper {
    public static TechBusinessValueEntity entityFromId(Long id) {
        TechBusinessValueEntity entity = new TechBusinessValueEntity();
        entity.id = id;
        return entity;
    }
    
}
