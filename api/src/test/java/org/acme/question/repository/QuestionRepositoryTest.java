package org.acme.question.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.QuestionData;
import org.acme.question.domain.exception.QuestionNotFoundException;
import org.acme.question.domain.input.UpdateQuestion;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
import org.acme.question.infra.database.repository.QuestionEntityRepository;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class QuestionRepositoryTest {
    private QuestionEntityRepository questionEntityRepository = new QuestionEntityRepository();

    @Inject
    EntityManager em;

    @Inject
    QuestionData questionData;

    @BeforeEach
    @Transactional
    public void setup() {
        questionData.setup();
    }

    @AfterEach
    @Transactional
    public void clear() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        ApplicationEntity.deleteAll();
        CategoryODAChildEntity.deleteAll();
        CategoryODAParentEntity.deleteAll();
        ClasseEntity.deleteAll();
        DepartementEntity.deleteAll();
        DocumentationEntity.deleteAll();
        CostEntity.deleteAll();
        TechBusinessValueEntity.deleteAll();
        ApplicationHistoryEntity.deleteAll();
        QuestionGroupEntity.deleteAll();
        QuestionEntity.deleteAll();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    @Test
    @TestTransaction
    public void testSaveQuestion() {
        var createInput = questionData.createQuestion();
        var savedQuestion = questionEntityRepository.save(createInput);

        assertNotNull(savedQuestion);
        assertEquals(createInput.getText(), savedQuestion.getText());
    }

    @Test
    @TestTransaction
    public void testFindAllQuestion() {
        var question = questionEntityRepository.findAll();

        assertNotNull(question);
        assertTrue(question.size() > 0);

        for (var q : question) {
            assertFalse(q.isDeleted());
        }
    }

    @Test
    @TestTransaction
    public void testFindQuestionById() throws Exception {
        var createdQuestion = questionData.getQuestion();
        var foundQuestion = questionEntityRepository.findById(createdQuestion.id);

        assertNotNull(foundQuestion);
        assertEquals(createdQuestion.id, foundQuestion.getId());
        assertEquals(createdQuestion.getText(), foundQuestion.getText());
    }

    @Test
    @TestTransaction
    public void testUpdateQuestion() throws Exception {
        var createdQuestion = questionData.getQuestion();
        var updateInput = questionData.updateQuestion();

        var updatedQuestion = questionEntityRepository.update(createdQuestion.id, updateInput);

        assertNotNull(updatedQuestion);
        assertEquals(updateInput.getText(), updatedQuestion.getText());
    }

    @Test
    @TestTransaction
    public void testUpdatePartialQuestion() throws QuestionNotFoundException {
        var createdQuestion = questionData.getQuestion();
        var updateInput = new UpdateQuestion();
        updateInput.setText("text only");

        var updatedQuestion = questionEntityRepository.update(createdQuestion.id, updateInput);

        assertNotNull(updatedQuestion);
        assertEquals(updateInput.getText(), updatedQuestion.getText());
    }

    @Test
    @TestTransaction
    public void testDeleteQuestion() throws QuestionNotFoundException {
        var createdQuestion = questionData.getQuestion();
        var deletedQuestion = questionEntityRepository.deleteById(createdQuestion.id);

        assertNotNull(deletedQuestion);
        assertTrue(deletedQuestion.isDeleted());
    }

    @Test
    @TestTransaction
    public void testFindQuestionByIdThrowsException() {
        assertThrows(QuestionNotFoundException.class, () -> questionEntityRepository.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateQuestionThrowsException() {
        var updateInput = questionData.updateQuestion();
        assertThrows(QuestionNotFoundException.class,
                () -> questionEntityRepository.update(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteQuestionThrowsException() {
        assertThrows(QuestionNotFoundException.class, () -> questionEntityRepository.deleteById(999L));
    }

}
