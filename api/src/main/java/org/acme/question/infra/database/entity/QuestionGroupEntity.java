package org.acme.question.infra.database.entity;

import java.util.ArrayList;
import java.util.List;
import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.model.QuestionGroup;
import org.acme.question.domain.model.QuestionGroupeType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionGroupEntity extends PanacheEntity {
    Long id;
    private String text;
    private int coeff;
    private QuestionGroupeType type;
    private String borderColor;
    @OneToMany(mappedBy = "questionGroup")
    private List<QuestionEntity> questionEntities;
    private boolean isDeleted;

    public QuestionGroupEntity(CreateQuestionGroup questionGroup) {
        this.text = questionGroup.getText();
        this.coeff = questionGroup.getCoeff();
        this.type = questionGroup.getType();
        this.borderColor = questionGroup.getBorderColor();
        this.isDeleted = false;
    }

    public QuestionGroup toQuestionGroup() {
        return new QuestionGroup(id, text, coeff, type, borderColor, questions(), isDeleted);
    }

    public List<Question> questions() {
        if (questionEntities == null)
            return new ArrayList<>();
        return questionEntities.stream().map(QuestionEntity::toQuestion).toList();
    }

    public QuestionGroupEntity updateQuestion(UpdateQuestionGroup question) {
        if (question.getText() != null) {
            this.text = question.getText();
        }

        if (question.getCoeff() != 0) {
            this.coeff = question.getCoeff();
        }

        if (question.getType() != null) {
            this.type = question.getType();
        }

        if (question.getBorderColor() != null) {
            this.borderColor = question.getBorderColor();
        }
        return this;
    }

}
