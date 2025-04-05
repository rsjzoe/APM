package org.acme.question.domain.port.out;

import java.util.List;

import org.acme.question.domain.exception.QuestionGroupNotFoundException;
import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;

public interface QuestionGroupRepository {
    QuestionGroup save(CreateQuestionGroup question);

    QuestionGroup update(Long id, UpdateQuestionGroup question) throws QuestionGroupNotFoundException;

    QuestionGroup findById(Long id) throws QuestionGroupNotFoundException;

    QuestionGroup deleteById(Long id) throws QuestionGroupNotFoundException;

    List<QuestionGroup> findAll();

}
