package org.acme.departement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.departement.infra.database.DepartementEntityRepository;
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
public class DepartementRepositoryTest {
    @Inject
    EntityManager em;

    @Inject
    DepartementEntityRepository repository;

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
    public void testGetListDepartement() {
        DepartementEntity d1 = new DepartementEntity();
        d1.name = "RH";
        d1.persist();

        DepartementEntity d2 = new DepartementEntity();
        d2.name = "IT";
        d2.persist();

        List<Departement> departements = repository.getListDepartement();

        assertEquals(2, departements.size());
        List<String> names = departements.stream().map(Departement::getName).toList();
        assertTrue(names.contains("RH"));
        assertTrue(names.contains("IT"));
    }

    @Test
    @TestTransaction
    public void testFindById() throws DepartementNotFoundException {
        DepartementEntity d = new DepartementEntity();
        d.name = "Finance";
        d.persist();

        Departement result = repository.findById(d.id);

        assertNotNull(result);
        assertEquals("Finance", result.getName());
        assertEquals(d.id, result.getId());
    }

    @Test
    @TestTransaction
    public void testFindByIdNotFound() {
        Long id = 9999L;

        assertThrows(DepartementNotFoundException.class, () -> {
            repository.findById(id);
        });
    }
}
