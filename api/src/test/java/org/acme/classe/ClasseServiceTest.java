package org.acme.classe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.app.ClasseService;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ClasseServiceTest {
    @Inject
    EntityManager em;

    @Inject
    private ClasseService classeService;

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
    void testCreate() {
        CreateClasseInput input = new CreateClasseInput("Classe A", "Description");
        ClasseOutput result = classeService.create(input);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Classe A", result.getName());
        assertEquals("Description", result.getDescription());
        assertFalse(result.isDeleted());
    }

    @Test
    @TestTransaction
    void testGetListAll() {
        classeService.create(new CreateClasseInput("Classe A", "Description"));
        classeService.create(new CreateClasseInput("Classe B", "Description 2"));

        List<ClasseOutput> classes = classeService.getListAll();

        assertNotNull(classes);
        assertEquals(2, classes.size());
        assertEquals("Classe A", classes.get(0).getName());
        assertEquals("Classe B", classes.get(1).getName());
    }

    @Test
    @TestTransaction
    void testFindById() throws ClasseNotFoundException {
        ClasseOutput created = classeService.create(new CreateClasseInput("Classe A",
                "Description"));
        ClasseOutput found = classeService.findById(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
        assertEquals("Classe A", found.getName());
    }

    @Test
    @TestTransaction
    void testFindByIdNotFound() throws ClasseNotFoundException {
        assertThrows(ClasseNotFoundException.class, () -> classeService.findById(987654321L));
    }

    @Test
    @TestTransaction
    void testUpdate() throws ClasseNotFoundException {
        ClasseOutput created = classeService.create(new CreateClasseInput("Classe A",
                "Description"));
        UpdateClasse updateClasse = new UpdateClasse("Classe A Updated", "Description Updated");

        ClasseOutput updated = classeService.update(created.getId(), updateClasse);

        assertNotNull(updated);
        assertEquals("Classe A Updated", updated.getName());
        assertEquals("Description Updated", updated.getDescription());
    }

    @Test
    @TestTransaction
    void testUpdateNotFound() throws ClasseNotFoundException {
        assertThrows(ClasseNotFoundException.class,
                () -> classeService.update(98321L, new UpdateClasse("Classe A Updated", "Description Updated")));
    }

    @Test
    @TestTransaction
    void testDeleteById() throws ClasseNotFoundException {
        ClasseOutput created = classeService.create(new CreateClasseInput("Classe A", "Description"));
        ClasseOutput deleted = classeService.deleteById(created.getId());

        assertNotNull(deleted);
        assertTrue(deleted.isDeleted());

    }

    @Test
    @TestTransaction
    void testDeleteByIdNotFound() throws ClasseNotFoundException {
        assertThrows(ClasseNotFoundException.class, () -> classeService.deleteById(987654321L));
    }
}
