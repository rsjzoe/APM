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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ApplicationServiceTest {
    @Inject
    ApplicationData applicationData;

    @Inject
    ApplicationService applicationService;

    @BeforeEach
    @Transactional
    public void setup() {
        applicationData.setup();
    }

    @Test
    public void testListAllApplications() {
        List<ApplicationOutput> applications = applicationService.listAll();
        assertNotNull(applications);
        assertTrue(applications.size() > 0);
    }

    @Test
    public void testFindApplicationById() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        ApplicationOutput found = applicationService.findById(created.id);

        assertNotNull(found);
        assertEquals(created.id, found.getId());
    }

    @Test
    public void testDeleteApplication() throws ApplicationNotFoundException {
        var created = applicationData.getApplication1();
        ApplicationOutput deleted = applicationService.delete(created.id);

        assertNotNull(deleted);
        assertTrue(deleted.isDeleted());
    }

    @Test
    public void testDeletedApplications() throws ApplicationNotFoundException {
        List<ApplicationOutput> deletedApplications = applicationService.deletedApplication();
        assertNotNull(deletedApplications);
        assertTrue(deletedApplications.size() > 0);
    }

    @Test
    public void testFindByIdThrowsException() {
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.findById(999L));
    }

    @Test
    public void testDeleteThrowsException() {
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.delete(999L));
    }
}
