package org.acme.question.domain.port.in;

import java.util.List;

import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;

public interface QuestionGroupRest {
    QuestionGroup findById(Long id);

    QuestionGroup deleteById(Long id);

    List<QuestionGroup> findAll();

    QuestionGroup create(CreateQuestionGroup question);

    QuestionGroup update(Long id, UpdateQuestionGroup question);
}
