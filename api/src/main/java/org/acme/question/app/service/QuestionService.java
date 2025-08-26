package org.acme.question.app.service;

import java.util.List;

import org.acme.SocketIOServerProvider;
import org.acme.question.domain.exception.QuestionNotFoundException;
import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.port.out.QuestionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuestionService {
    @Inject
    QuestionRepository questionRepository;

    @Inject
    SocketIOServerProvider socketio;

    @Transactional
    public Question save(CreateQuestion newQuestion) {
        var created = questionRepository.save(newQuestion);
        socketio.sendEvent("refetch_question");
        return created;
    }

    @Transactional
    public Question update(Long id, UpdateQuestion question) throws QuestionNotFoundException {
        var updated = questionRepository.update(id, question);
        socketio.sendEvent("refetch_question");
        return updated;
    }

    @Transactional
    public Question findById(Long id) throws QuestionNotFoundException {
        return questionRepository.findById(id);
    }

    @Transactional
    public Question deleteById(Long id) throws QuestionNotFoundException {
        var deleted = questionRepository.deleteById(id);
        socketio.sendEvent("refetch_question");
        return deleted;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
