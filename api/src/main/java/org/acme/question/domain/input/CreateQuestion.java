package org.acme.question.domain.input;

public class CreateQuestion {
    private String text;
    private String borderColor;

    public CreateQuestion() {
    }

    public CreateQuestion(String text, String borderColor) {
        this.text = text;
        this.borderColor = borderColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
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
                ", borderColor='" + borderColor + '\'' +
                '}';
    }
}
