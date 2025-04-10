package org.acme.question.service;

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
import org.acme.question.app.service.QuestionGroupService;
import org.acme.question.domain.exception.QuestionGroupNotFoundException;
import org.acme.question.domain.input.UpdateQuestionGroup;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
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
public class QuestionGroupServiceTest {
    @Inject
    EntityManager em;

    @Inject
    QuestionData questionData;

    @Inject
    private QuestionGroupService questionGroupService;

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
    public void testSaveQuestionGroup() {
        var createInput = questionData.createQuestionGroup();
        var savedQuestionGroup = questionGroupService.save(createInput);

        assertNotNull(savedQuestionGroup);
        assertEquals(createInput.getText(), savedQuestionGroup.getText());
        assertEquals(createInput.getBorderColor(), savedQuestionGroup.getBorderColor());
    }

    @Test
    @TestTransaction
    public void testFindAllQuestionGroup() {
        var questionGroup = questionGroupService.findAll();

        assertNotNull(questionGroup);
        assertTrue(questionGroup.size() > 0);

        for (var category : questionGroup) {
            assertFalse(category.isDeleted());
        }
    }

    @Test
    @TestTransaction
    public void testFindQuestionGroupById() throws QuestionGroupNotFoundException {
        var createdQuestionGroup = questionData.getQuestionGroup();
        var foundParent = questionGroupService.findById(createdQuestionGroup.getId());

        assertNotNull(foundParent);
        assertEquals(createdQuestionGroup.getId(), foundParent.getId());
        assertEquals(createdQuestionGroup.getText(), foundParent.getText());
    }

    @Test
    @TestTransaction
    public void testUpdateQuestionGroup() throws QuestionGroupNotFoundException {
        var createdQuestionGroup = questionData.getQuestionGroup();
        var updateInput = questionData.updateQuestionGroup();

        var updatedQuestionGroup = questionGroupService.update(createdQuestionGroup.getId(), updateInput);

        assertNotNull(updatedQuestionGroup);
        assertEquals(updateInput.getText(), updatedQuestionGroup.getText());
        assertEquals(updateInput.getBorderColor(), updatedQuestionGroup.getBorderColor());
    }

    @Test
    @TestTransaction
    public void testUpdatePartialQuestionGroup() throws Exception {
        var createdQuestionGroup = questionData.getQuestionGroup();
        var updateInput = new UpdateQuestionGroup();
        updateInput.setText("text only");

        var updatedQuestionGroup = questionGroupService.update(createdQuestionGroup.getId(), updateInput);

        assertNotNull(updatedQuestionGroup);
        assertEquals(updateInput.getText(), updatedQuestionGroup.getText());
    }

    @Test
    @TestTransaction
    public void testDeleteQuestionGroup() throws QuestionGroupNotFoundException {
        var createdQuestionGroup = questionData.getQuestionGroup();
        var deletedQuestionGroup = questionGroupService.deleteById(createdQuestionGroup.getId());

        assertNotNull(deletedQuestionGroup);
        assertTrue(deletedQuestionGroup.isDeleted());
    }

    @Test
    @TestTransaction
    public void testFindQuestionGroupByIdThrowsException() {
        assertThrows(QuestionGroupNotFoundException.class, () -> questionGroupService.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateQuestionGroupThrowsException() {
        var updateInput = questionData.updateQuestionGroup();
        assertThrows(QuestionGroupNotFoundException.class,
                () -> questionGroupService.update(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteQuestionGroupThrowsException() {
        assertThrows(QuestionGroupNotFoundException.class, () -> questionGroupService.deleteById(999L));
    }

}
