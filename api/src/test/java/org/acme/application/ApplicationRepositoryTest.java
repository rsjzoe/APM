package org.acme.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.query.ApplicationQuery;
import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationEntityRepository;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

@QuarkusTest
public class ApplicationRepositoryTest {
    private ApplicationEntityRepository repository = new ApplicationEntityRepository();
    @Inject
    ApplicationData applicationData;

    @Inject
    EntityManager em;

    @BeforeEach
    @Transactional
    public void setup() {
        applicationData.setup();
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
    public void testCreateApplication() {
        CreateApplicationRepositoryInput input = applicationData.createApplicationInput();
        ApplicationOutput output = repository.create(input);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
        assertEquals(input.getDescription(), output.getDescription());
    }

    @Test
    @TestTransaction
    public void testListAllApplications() {
        List<ApplicationOutput> applications = repository.listAll(new ApplicationQuery());
        assertNotNull(applications);
        assertTrue(applications.size() > 0);
    }

    @Test
    @TestTransaction
    public void testFindApplicationById() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        ApplicationOutput found = repository.findById(created.id);

        assertNotNull(found);
        assertEquals(created.id, found.getId());
    }

    @Test
    @TestTransaction
    public void testUpdateApplication() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        UpdateApplicationRepositoryInput updateInput = applicationData.updateApplicationInput();

        ApplicationOutput updated = repository.update(created.id, updateInput);

        assertNotNull(updated);
        assertEquals(updateInput.getName(), updated.getName());
        assertEquals(updateInput.getDescription(), updated.getDescription());
    }

    @Test
    @TestTransaction
    public void testDeleteApplication() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        ApplicationOutput deleted = repository.delete(created.id);

        assertNotNull(deleted);
        assertTrue(deleted.isDeleted());
    }

    @Test
    @TestTransaction
    public void testDeletedApplications() throws ApplicationNotFoundException {
        List<ApplicationOutput> deletedApplications = repository.deletedApplication();
        assertNotNull(deletedApplications);
        assertTrue(deletedApplications.size() > 0);
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        assertThrows(ApplicationNotFoundException.class, () -> repository.findById(999L));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        UpdateApplicationRepositoryInput updateInput = new UpdateApplicationRepositoryInput();
        assertThrows(ApplicationNotFoundException.class, () -> repository.update(999L, updateInput));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        assertThrows(ApplicationNotFoundException.class, () -> repository.delete(999L));
    }
}
