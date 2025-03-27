package org.acme.question.infra.database;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionEntity extends PanacheEntity {
    private String text;
    @ManyToOne
    private QuestionGroupEntity questionGroup;
    private boolean isDeleted;

    public QuestionEntity(CreateQuestion question) {
        this.text = question.getText();
        this.questionGroup = QuestionGroupEntityHelper.entityFromId(question.getQuestionGroupId());
        this.isDeleted = false;
    }

    public QuestionEntity updateQuestion(UpdateQuestion question) {
        this.text = question.getText();
        this.questionGroup = QuestionGroupEntityHelper.entityFromId(question.getQuestionGroupId());
        return this;
    }

    public Question toQuestion() {
        return new Question(id, text, isDeleted);
    }
}
