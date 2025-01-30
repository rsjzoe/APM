package org.acme.question.domain.model;

public class Question {
    private Long id;
    private String text;
    private String borderColor;

    public Question() {
    }

    public Question(Long id, String text, String borderColor) {
        this.id = id;
        this.text = text;
        this.borderColor = borderColor;
    }
    
    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
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
