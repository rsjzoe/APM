package org.acme.question.domain.input;

public class CreateQuestion {
    private String text;

    public CreateQuestion() {
    }

    public CreateQuestion(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CreateQuestion{" +

                ", text='" + text + '\'' +
                '}';
    }
}
