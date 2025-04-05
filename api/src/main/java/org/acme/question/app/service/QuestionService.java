package org.acme.question.app.service;

import java.util.List;

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

    @Transactional
    public Question save(CreateQuestion newQuestion) {
        return questionRepository.save(newQuestion);
    }

    @Transactional
    public Question update(Long id, UpdateQuestion question) throws QuestionNotFoundException {
        return questionRepository.update(id, question);
    }

    @Transactional
    public Question findById(Long id) throws QuestionNotFoundException {
        return questionRepository.findById(id);
    }

    @Transactional
    public Question deleteById(Long id) throws QuestionNotFoundException {
        return questionRepository.deleteById(id);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
