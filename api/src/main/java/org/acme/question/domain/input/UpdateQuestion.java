package org.acme.question.domain.input;

public class UpdateQuestion {
    private String text;
    private String borderColor;


    public UpdateQuestion() {
    }

    public UpdateQuestion(String text, String borderColor) {
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
        return "UpdateQuestion{" +

                ", text='" + text + '\'' +
                '}';
    }
}
