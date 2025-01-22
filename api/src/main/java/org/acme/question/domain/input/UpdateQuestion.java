package org.acme.question.domain.input;

public class UpdateQuestion {
    private String text;

    public UpdateQuestion() {
    }

    public UpdateQuestion(String text) {
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
        return "UpdateQuestion{" +

                ", text='" + text + '\'' +
                '}';
    }
}
