package org.acme.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.output.PaginationOutput;
import org.acme.application.domain.query.ApplicationQuery;
import org.acme.application.infra.database.ApplicationEntity;
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
import org.acme.user.UserData;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ApplicationServiceTest {
    @Inject
    EntityManager em;

    @Inject
    ApplicationData applicationData;

    @Inject
    ApplicationService applicationService;

    @Inject
    UserData userData;

    @BeforeEach
    @Transactional
    public void setup() {
        applicationData.setup();
        userData.setup();
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
        userData.clear();
    }

    @Test
    @TestTransaction
    public void testListAllApplications() {
        PaginationOutput<ApplicationOutput> applications = applicationService.listAll(new ApplicationQuery());
        assertNotNull(applications.getItems());
        assertTrue(applications.getItems().size() > 0);
    }

    @Test
    @TestTransaction
    public void testFindApplicationById() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        ApplicationOutput found = applicationService.findById(created.id);

        assertNotNull(found);
        assertEquals(created.id, found.getId());
    }

    @Test
    @TestTransaction
    public void testDeleteApplication()
            throws ApplicationNotFoundException, VerificationTokenException, UserNotFoundException {
        String token = userData.getUserAdminToken().getAccessToken();
        var created = applicationData.getApplication1();
        ApplicationOutput deleted = applicationService.delete(created.id, token);

        assertNotNull(deleted);
        assertTrue(deleted.isDeleted());
    }

    @Test
    @TestTransaction
    public void testDeletedApplications() throws ApplicationNotFoundException {
        List<ApplicationOutput> deletedApplications = applicationService.deletedApplication();
        assertNotNull(deletedApplications);
        assertTrue(deletedApplications.size() > 0);
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.findById(999L));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        String token = userData.getUserAdminToken().getAccessToken();
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.delete(999L, token));
    }
}
