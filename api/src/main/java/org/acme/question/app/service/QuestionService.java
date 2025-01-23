package org.acme.question.app.service;

import java.util.List;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.domain.port.out.QuestionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QuestionService {
    @Inject
    QuestionRepository questionRepository;

    public Question save(CreateQuestion newQuestion) {
        return questionRepository.save(newQuestion);
    }

    public Question update(Long id, UpdateQuestion question) {
        return questionRepository.update(id, question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id);
    }

    public Question deleteById(Long id) {
        return questionRepository.deleteById(id);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
