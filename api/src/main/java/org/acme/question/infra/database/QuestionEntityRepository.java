package org.acme.question.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.question.domain.exception.QuestionNotFoundException;
import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.port.out.QuestionRepository;

public class QuestionEntityRepository implements QuestionRepository {

    @Override
    public Question save(CreateQuestion question) {
        QuestionEntity data = new QuestionEntity(question);
        data.persist();
        return data.toQuestion();
    }

    @Override
    public Question update(Long id, UpdateQuestion question) throws QuestionNotFoundException {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null) {
            throw new QuestionNotFoundException();
        }
        data.updateQuestion(question);
        return data.toQuestion();
    }

    @Override
    public Question findById(Long id) throws QuestionNotFoundException {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null) {
            throw new QuestionNotFoundException();
        }
        return data.toQuestion();
    }

    @Override
    public Question deleteById(Long id) throws QuestionNotFoundException {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null) {
            throw new QuestionNotFoundException();
        }
        data.setDeleted(true);
        data.persist();
        return data.toQuestion();
    }

    @Override
    public List<Question> findAll() {
        List<QuestionEntity> data = QuestionEntity.list("isDeleted =? 1", false);
        return data.stream()
                .map(entity -> (entity).toQuestion())
                .collect(Collectors.toList());
    }

}
