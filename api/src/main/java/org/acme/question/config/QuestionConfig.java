package org.acme.question.config;

import org.acme.question.domain.port.out.QuestionRepository;
import org.acme.question.infra.database.QuestionEntityRepository;

import jakarta.enterprise.inject.Produces;

public class QuestionConfig {
    @Produces
    public QuestionRepository questionRepository() {
        return new QuestionEntityRepository();
    }
}
