package org.acme.question.domain.port.out;

import java.util.List;

import org.acme.question.domain.exception.QuestionNotFoundException;
import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;

public interface QuestionRepository {
    Question save(CreateQuestion question);

    Question update(Long id, UpdateQuestion question) throws QuestionNotFoundException;

    Question findById(Long id) throws QuestionNotFoundException;

    Question deleteById(Long id) throws QuestionNotFoundException;

    List<Question> findAll();
}
