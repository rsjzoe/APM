package org.acme.classe;

import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.classe.infra.database.ClasseEntityRepository;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ClasseRepositoryTest {
    @Inject
    EntityManager em;

    private ClasseEntityRepository repository;

    @BeforeEach
    @Transactional
    public void setup() {
        repository = new ClasseEntityRepository();
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
    public void testCreate() {
        CreateClasseInput input = new CreateClasseInput("classe 1", "pas mal");
        ClasseOutput created = repository.create(input);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("classe 1", created.getName());
        Assertions.assertEquals("pas mal", created.getDescription());
        Assertions.assertFalse(created.isDeleted());
    }

    @Test
    @TestTransaction

    public void testGetListAll() {
        repository.create(new CreateClasseInput("classe 1", "pas mal"));
        repository.create(new CreateClasseInput("classe 2", "tres bien"));

        List<ClasseOutput> classes = repository.getListAll();

        Assertions.assertFalse(classes.isEmpty());
        Assertions.assertEquals(2, classes.size());
        Assertions.assertTrue(classes.size() > 0);
    }

    @Test
    @TestTransaction
    public void testFindById() throws ClasseNotFoundException {
        ClasseOutput created = repository.create(new CreateClasseInput("classe 1",
                "pas mal"));
        ClasseOutput found = repository.findById(created.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getId(), found.getId());
        Assertions.assertEquals("classe 1", found.getName());
    }

    @Test
    @TestTransaction
    public void testUpdate() throws ClasseNotFoundException {
        ClasseOutput created = repository.create(new CreateClasseInput("classe 1",
                "description 1"));
        UpdateClasse updateData = new UpdateClasse("classe 2", "description 2");

        ClasseOutput updated = repository.update(created.getId(), updateData);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("classe 2", updated.getName());
        Assertions.assertEquals("description 2", updated.getDescription());
    }

    @Test
    @TestTransaction
    public void testDeleteById() throws ClasseNotFoundException {
        ClasseOutput created = repository.create(new CreateClasseInput("Biologie",
                "Cours de biologie"));

        ClasseOutput deleted = repository.deleteById(created.getId());

        Assertions.assertNotNull(deleted);
        Assertions.assertTrue(deleted.isDeleted());

        List<ClasseOutput> classes = repository.getListAll();
        Assertions.assertFalse(classes.stream().anyMatch(c -> c.getId().equals(created.getId())));
    }
}
