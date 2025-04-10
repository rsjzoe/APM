package org.acme.question.infra.database.entity;

public class QuestionGroupEntityHelper {
    public static QuestionGroupEntity entityFromId(Long id){
        QuestionGroupEntity entity = new QuestionGroupEntity();
        entity.id = id;
        return entity;
    }
}
