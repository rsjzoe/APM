package org.acme.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.CategoryData;
import org.acme.category.app.CategoryODAChildService;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.category.domain.input.UpdateCategoryODAChild;
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
public class CategoryChildServiceTest {
    @Inject
    EntityManager em;

    @Inject
    CategoryODAChildService childService;

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
    public void testSaveCategoryChild() {
        var createInput = categoryData.createCategoryODAChild();
        var savedCategory = childService.save(createInput);

        assertNotNull(savedCategory);
        assertEquals(createInput.getName(), savedCategory.getName());
        assertEquals(createInput.getParentId(), savedCategory.getParentId());
    }

    @Test
    @TestTransaction
    public void testFindAllCategoryChildren() {
        var categoryChildren = childService.findAll();

        assertNotNull(categoryChildren);
        assertTrue(categoryChildren.size() > 0);

        for (var category : categoryChildren) {
            assertFalse(category.getIsDelete());
        }
    }

    @Test
    @TestTransaction
    public void testFindCategoryChildById() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var foundChild = childService.findById(createdChild.id);

        assertNotNull(foundChild);
        assertEquals(createdChild.id, foundChild.getId());
        assertEquals(createdChild.getName(), foundChild.getName());
    }

    @Test
    @TestTransaction
    public void testUpdateCategoryChild() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var updateInput = categoryData.updateCategoryODAChild();

        var updatedChild = childService.updateById(createdChild.id, updateInput);

        assertNotNull(updatedChild);
        assertEquals(updateInput.getName(), updatedChild.getName());
        assertEquals(updateInput.getParentId(), updatedChild.getParentId());
    }

    @Test
    @TestTransaction
    public void testUpdatePartialCategoryChild() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var updateInput = new UpdateCategoryODAChild();
        updateInput.setName("name only");

        var updatedChild = childService.updateById(createdChild.id, updateInput);

        assertNotNull(updatedChild);
        assertEquals(updateInput.getName(), updatedChild.getName());
    }

    @Test
    @TestTransaction
    public void testDeleteCategoryChild() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var deletedChild = childService.deleteById(createdChild.id);

        assertNotNull(deletedChild);
        assertTrue(deletedChild.getIsDelete());
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        assertThrows(CategoryODAChildNotFoundException.class, () -> childService.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var updateInput = categoryData.updateCategoryODAChild();
        assertThrows(CategoryODAChildNotFoundException.class,
                () -> childService.updateById(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        assertThrows(CategoryODAChildNotFoundException.class, () -> childService.deleteById(999L));
    }

}
