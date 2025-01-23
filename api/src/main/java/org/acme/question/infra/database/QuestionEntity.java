package org.acme.question.infra.database;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class QuestionEntity extends PanacheEntity {
    private Long id;
    private String text;

    public QuestionEntity() {
    }

    public QuestionEntity(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public QuestionEntity(CreateQuestion question) {
        this.text = question.getText();
    }

    public Question toQuestion() {
        return new Question(id, text);
    }

    public QuestionEntity updateQuestion(UpdateQuestion question) {
        this.text = question.getText();
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
