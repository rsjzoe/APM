package org.acme.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.InvalidApplicationException;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ApplicationServiceCreateTest {
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
    @TestTransaction
    public void testCreateApplicationSuccess() throws Exception {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        ApplicationOutput output = applicationService.create(input);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
        assertEquals(input.getDescription(), output.getDescription());
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidDates() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setStartDate(LocalDateTime.now().plusDays(1));

        assertThrows(InvalidApplicationException.class, () -> applicationService.create(input));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidCost() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setCostWithoutApp(new CreateCostWithoutApp(-1000.0, 500.0));

        assertThrows(InvalidCostException.class, () -> applicationService.create(input));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidTechBusinessValue() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setTechBusinessValueWithoutApp(new CreateTechBusinessValueWithoutApp(5.5, 2.0));

        assertThrows(InvalidTechBusinessValueException.class, () -> applicationService.create(input));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationCategoryNotFound() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setCategoryId(9999L);

        assertThrows(CategoryODAChildNotFoundException.class, () -> applicationService.create(input));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationDepartementNotFound() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setDepartementId(9999L);

        assertThrows(DepartementNotFoundException.class, () -> applicationService.create(input));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationClasseNotFound() {
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setClasseId(9999L);

        assertThrows(ClasseNotFoundException.class, () -> applicationService.create(input));
    }
}
