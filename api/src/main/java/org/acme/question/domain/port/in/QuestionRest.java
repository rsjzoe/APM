package org.acme.question.domain.port.in;

import java.util.List;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;

public interface QuestionRest {
    Question save(CreateQuestion question);

    Question update(Long id, UpdateQuestion question);

    Question findById(Long id);

    Question deleteById(Long id);

    List<Question> findAll();
}
