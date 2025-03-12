package org.acme.question.domain.input;

import org.acme.question.domain.model.QuestionGroupeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionGroup {
    private String text;
    private int coeff;
    private QuestionGroupeType type;
    private String borderColor;

}
