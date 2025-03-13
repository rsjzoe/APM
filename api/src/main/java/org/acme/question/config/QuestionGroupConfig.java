package org.acme.question.config;

import org.acme.question.domain.port.out.QuestionGroupRepository;
import org.acme.question.infra.database.QuestionGroupEntityRepository;

import jakarta.enterprise.inject.Produces;

public class QuestionGroupConfig {
    @Produces
    public QuestionGroupRepository questionGroupRepository() {
        return new QuestionGroupEntityRepository();
    }
}
