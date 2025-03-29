package org.acme.category.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.category.CategoryData;
import org.acme.category.domain.exception.CategoryODAParentNotFoundException;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.infra.out.repositoryImpl.CategoryODAParentEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class CategoryParentRepositoryTest {
    private CategoryODAParentEntityRepository parentEntityRepository = new CategoryODAParentEntityRepository();

    @Inject
    CategoryData categoryData;

    @BeforeEach
    @Transactional
    public void setup() {
        categoryData.setup();
    }

    @Test
    @TestTransaction
    public void testSaveCategoryParent() {
        var createInput = categoryData.createCategoryODAParent();
        var savedCategory = parentEntityRepository.save(createInput);

        assertNotNull(savedCategory);
        assertEquals(createInput.getName(), savedCategory.getName());
        assertEquals(createInput.getBgColor(), savedCategory.getBgColor());
    }

    @Test
    @TestTransaction
    public void testFindAllCategoryParentren() {
        var categoryParent = parentEntityRepository.findAll();

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
        var foundParent = parentEntityRepository.findById(createdParent.id);

        assertNotNull(foundParent);
        assertEquals(createdParent.id, foundParent.getId());
        assertEquals(createdParent.getName(), foundParent.getName());
    }

    @Test
    @TestTransaction
    public void testUpdateCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var updateInput = categoryData.updateCategoryODAParent();

        var updatedParent = parentEntityRepository.updateById(createdParent.id, updateInput);

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

        var updatedParent = parentEntityRepository.updateById(createdParent.id, updateInput);

        assertNotNull(updatedParent);
        assertEquals(updateInput.getName(), updatedParent.getName());
    }

    @Test
    @TestTransaction
    public void testDeleteCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var deletedParent = parentEntityRepository.deleteById(createdParent.id);

        assertNotNull(deletedParent);
        assertTrue(deletedParent.getIsDelete());
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        assertThrows(CategoryODAParentNotFoundException.class, () -> parentEntityRepository.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var updateInput = categoryData.updateCategoryODAParent();
        assertThrows(CategoryODAParentNotFoundException.class,
                () -> parentEntityRepository.updateById(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        assertThrows(CategoryODAParentNotFoundException.class, () -> parentEntityRepository.deleteById(999L));
    }

}
