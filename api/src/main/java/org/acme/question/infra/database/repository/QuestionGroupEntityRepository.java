package org.acme.question.infra.database.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.question.domain.exception.QuestionGroupNotFoundException;
import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;
import org.acme.question.domain.port.out.QuestionGroupRepository;
import org.acme.question.infra.database.entity.QuestionGroupEntity;

public class QuestionGroupEntityRepository implements QuestionGroupRepository {

    @Override
    public QuestionGroup save(CreateQuestionGroup newQuestion) {
        QuestionGroupEntity data = new QuestionGroupEntity(newQuestion);
        data.persist();
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup update(Long id, UpdateQuestionGroup updateQuestion) throws QuestionGroupNotFoundException {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null) {
            throw new QuestionGroupNotFoundException();
        }
        data.updateQuestion(updateQuestion);
        data.persist();
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup findById(Long id) throws QuestionGroupNotFoundException {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null) {
            throw new QuestionGroupNotFoundException();
        }
        return data.toQuestionGroup();
    }

    @Override
    public QuestionGroup deleteById(Long id) throws QuestionGroupNotFoundException {
        QuestionGroupEntity data = QuestionGroupEntity.findById(id);
        if (data == null) {
            throw new QuestionGroupNotFoundException();
        }
        data.setDeleted(true);
        data.persist();
        return data.toQuestionGroup();
    }

    @Override
    public List<QuestionGroup> findAll() {
        List<QuestionGroupEntity> data = QuestionGroupEntity.list("isDeleted =? 1", false);
        return data.stream()
                .map(entity -> (entity).toQuestionGroup())
                .collect(Collectors.toList());
    }

}
