package org.acme.question.domain.port.in;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;

public interface QuestionRest {
    Question create(CreateQuestion question);
    Question update(Long id, UpdateQuestion question);
}
