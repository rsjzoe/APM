package org.acme.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.domain.model.Question;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@QuarkusTest
public class QuestionEntityRepositoryTest {

    QuestionEntityRepository questionEntityRepository = new QuestionEntityRepository();

    @BeforeEach
    @Transactional
    public void setup() {
        QuestionEntity.deleteAll();
    }

    @Test
    @Transactional
    public void testSave() {
        CreateQuestion newQuestion = new CreateQuestion("What is Java?","green");
        Question savedQuestion = questionEntityRepository.save(newQuestion);
        assertNotNull(savedQuestion);
        assertNotNull(savedQuestion.getId());
        assertEquals(newQuestion.getText(), savedQuestion.getText());
    }

    @Test
    @Transactional
    public void testFindById() {
        CreateQuestion newQuestion = new CreateQuestion("What is Python?", "red");
        Question savedQuestion = questionEntityRepository.save(newQuestion);

        Question foundQuestion = questionEntityRepository.findById(savedQuestion.getId());
        assertNotNull(foundQuestion);
        assertEquals(savedQuestion.getId(), foundQuestion.getId());
        assertEquals(savedQuestion.getText(), foundQuestion.getText());
    }

    @Test
    @Transactional
    public void testUpdate() {
        CreateQuestion newQuestion = new CreateQuestion("What is java?", "black");
        Question savedQuestion = questionEntityRepository.save(newQuestion);

        UpdateQuestion updateQuestion = new UpdateQuestion("What is Quarkus?", "red");
        Question updatedQuestion = questionEntityRepository.update(savedQuestion.getId(), updateQuestion);

        assertNotNull(updatedQuestion);
        assertEquals(savedQuestion.getId(), updatedQuestion.getId());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        CreateQuestion newQuestion = new CreateQuestion("What is Hibernate?", "yellow");
        Question savedQuestion = questionEntityRepository.save(newQuestion);

        Question deletedQuestion = questionEntityRepository.deleteById(savedQuestion.getId());
        assertNotNull(deletedQuestion);

        Question foundQuestion = questionEntityRepository.findById(savedQuestion.getId());
        assertNull(foundQuestion);
    }

    @Test
    @Transactional
    public void testFindAll() {
        CreateQuestion newQuestion1 = new CreateQuestion("What is Java?", "yellow");
        CreateQuestion newQuestion2 = new CreateQuestion("What is quarkus?", "yellow");

        questionEntityRepository.save(newQuestion1);
        questionEntityRepository.save(newQuestion2);

        List<Question> questions = questionEntityRepository.findAll();
        assertEquals(2, questions.size());
    }
}
