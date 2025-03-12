package org.acme.question.infra.database;

import java.util.List;
import java.util.stream.Collectors;

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
    public Question update(Long id, UpdateQuestion question) {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null)
            return null;
        data.updateQuestion(question);
        return data.toQuestion();
    }

    @Override
    public Question findById(Long id) {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null)
            return null;
        return data.toQuestion();
    }

    @Override
    public Question deleteById(Long id) {
        QuestionEntity data = QuestionEntity.findById(id);
        if (data == null)
            return null;
        data.delete();
        return data.toQuestion();
    }

    @Override
    public List<Question> findAll() {
        List<QuestionEntity> data = QuestionEntity.listAll();
        return data.stream()
                .map(entity -> (entity).toQuestion())
                .collect(Collectors.toList());
    }

}
