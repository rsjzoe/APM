package org.acme.question.app.service;

import java.util.List;

import org.acme.SocketIOServerProvider;
import org.acme.question.domain.exception.QuestionGroupNotFoundException;
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

    @Inject
    SocketIOServerProvider socketio;

    @Transactional
    public QuestionGroup save(CreateQuestionGroup newQuestion) {
        var created = questionGroupRepository.save(newQuestion);
        socketio.sendEvent("refetch_question");
        return created;
    }

    @Transactional
    public QuestionGroup update(Long id, UpdateQuestionGroup question) throws QuestionGroupNotFoundException {
        var updated = questionGroupRepository.update(id, question);
        socketio.sendEvent("refetch_question");
        return updated;
    }

    @Transactional
    public QuestionGroup findById(Long id) throws QuestionGroupNotFoundException {
        return questionGroupRepository.findById(id);
    }

    @Transactional
    public QuestionGroup deleteById(Long id) throws QuestionGroupNotFoundException {
        var deleted = questionGroupRepository.deleteById(id);
        socketio.sendEvent("refetch_question");
        return deleted;
    }

    public List<QuestionGroup> findAll() {
        return questionGroupRepository.findAll();
    }
}
