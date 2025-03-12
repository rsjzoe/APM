package org.acme.question.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGroup {
    private Long id;
    private String text;
    private int coeff;
    private QuestionGroupeType type;
    private String borderColor;
    private List<Question> questions;
}
