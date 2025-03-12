package org.acme.question.app.service;

import java.util.List;

import org.acme.question.domain.input.CreateQuestionGroup;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.domain.model.QuestionGroup;
import org.acme.question.domain.port.out.QuestionGroupRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuestionGroupService {
    @Inject
    QuestionGroupRepository questionGroupRepository;

    @Transactional
    public QuestionGroup save(CreateQuestionGroup newQuestion) {
        return questionGroupRepository.save(newQuestion);
    }

    @Transactional
    public QuestionGroup update(Long id, UpdateQuestionGroup question) {
        return questionGroupRepository.update(id, question);
    }

    @Transactional
    public QuestionGroup findById(Long id) {
        return questionGroupRepository.findById(id);
    }

    @Transactional
    public QuestionGroup deleteById(Long id) {
        return questionGroupRepository.deleteById(id);
    }

    public List<QuestionGroup> findAll() {
        return questionGroupRepository.findAll();
    }
}
