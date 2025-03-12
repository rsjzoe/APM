package org.acme.question.infra.database;

public class QuestionEntityHelper {
    public static QuestionEntity entityFromId(Long id) {
        QuestionEntity entity = new QuestionEntity();
        entity.id = id;
        return entity;
    }
}
