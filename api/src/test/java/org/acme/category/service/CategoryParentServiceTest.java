package org.acme.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.CategoryData;
import org.acme.category.app.CategoryODAParentService;
import org.acme.category.domain.exception.CategoryODAParentNotFoundException;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
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
public class CategoryParentServiceTest {
    @Inject
    EntityManager em;

    @Inject
    private CategoryODAParentService parentService;

    @Inject
    CategoryData categoryData;

    @BeforeEach
    @Transactional
    public void setup() {
        categoryData.setup();
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
    public void testSaveCategoryParent() {
        var createInput = categoryData.createCategoryODAParent();
        var savedCategory = parentService.save(createInput);

        assertNotNull(savedCategory);
        assertEquals(createInput.getName(), savedCategory.getName());
        assertEquals(createInput.getBgColor(), savedCategory.getBgColor());
    }

    @Test
    @TestTransaction
    public void testFindAllCategoryParentren() {
        var categoryParent = parentService.findAll();

        assertNotNull(categoryParent);
        assertTrue(categoryParent.size() > 0);

        for (var category : categoryParent) {
            assertFalse(category.getIsDelete());
        }
    }

    @Test
    @TestTransaction
    public void testFindCategoryParentById() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var foundParent = parentService.findById(createdParent.id);

        assertNotNull(foundParent);
        assertEquals(createdParent.id, foundParent.getId());
        assertEquals(createdParent.getName(), foundParent.getName());
    }

    @Test
    @TestTransaction
    public void testUpdateCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var updateInput = categoryData.updateCategoryODAParent();

        var updatedParent = parentService.updateById(createdParent.id, updateInput);

        assertNotNull(updatedParent);
        assertEquals(updateInput.getName(), updatedParent.getName());
        assertEquals(updateInput.getBgColor(), updatedParent.getBgColor());
    }

    @Test
    @TestTransaction
    public void testUpdatePartialCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var updateInput = new UpdateCategoryODAParent();
        updateInput.setName("name only");

        var updatedParent = parentService.updateById(createdParent.id, updateInput);

        assertNotNull(updatedParent);
        assertEquals(updateInput.getName(), updatedParent.getName());
    }

    @Test
    @TestTransaction
    public void testDeleteCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var deletedParent = parentService.deleteById(createdParent.id);

        assertNotNull(deletedParent);
        assertTrue(deletedParent.getIsDelete());
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        assertThrows(CategoryODAParentNotFoundException.class, () -> parentService.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var updateInput = categoryData.updateCategoryODAParent();
        assertThrows(CategoryODAParentNotFoundException.class,
                () -> parentService.updateById(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        assertThrows(CategoryODAParentNotFoundException.class, () -> parentService.deleteById(999L));
    }

}
