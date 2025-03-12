package org.acme.question.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;
import org.acme.question.domain.port.out.QuestionGroupRepository;


public class QuestionGroupEntityRepository implements QuestionGroupRepository {

    @Override
    public QuestionGroup save(CreateQuestionGroup newQuestion) {
        QuestionGroupEntity data = new QuestionGroupEntity(newQuestion);
        data.persist();
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup update(Long id, UpdateQuestionGroup updateQuestion) {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null)
            return null;
        data.updateQuestion(updateQuestion);
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup findById(Long id) {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null)
            return null;
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup deleteById(Long id) {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null)
            return null;
        data.delete();
        return data.toQuestionGroup();
    }

    @Override
    public List<QuestionGroup> findAll() {
        List<QuestionGroupEntity> data = QuestionGroupEntity.listAll();
        return data.stream()
                .map(entity -> (entity).toQuestionGroup())
                .collect(Collectors.toList());
    }

}
