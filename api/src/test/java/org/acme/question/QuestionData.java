package org.acme.question;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroupeType;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

@ApplicationScoped
@Getter
public class QuestionData {
    private QuestionGroupEntity questionGroup;
    private QuestionEntity question;

    public void setup() {
        questionGroup = new QuestionGroupEntity(
                new CreateQuestionGroup("performance", 3, QuestionGroupeType.businessValue, "red"));
        questionGroup.persistAndFlush();

        question = new QuestionEntity(new CreateQuestion("c'est quoi quarkus?", questionGroup.getId()));
        question.setQuestionGroup(questionGroup);
        question.persistAndFlush();
    }

    public CreateQuestion createQuestion() {
        return new CreateQuestion("c'est quoi quarkus?", questionGroup.getId());
    }

    public UpdateQuestion updateQuestion() {
        return new UpdateQuestion("c'est quoi quarkus - Updated?", questionGroup.getId());
    }

    public CreateQuestionGroup createQuestionGroup() {
        return new CreateQuestionGroup("performance", 3, QuestionGroupeType.businessValue, "green");
    }

    public UpdateQuestionGroup updateQuestionGroup() {
        return new UpdateQuestionGroup("performance - Updated", 3, QuestionGroupeType.businessValue, "green");
    }
}
