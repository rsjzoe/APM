package org.acme.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
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

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ApplicationServiceUpdateTest {
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
    public void testUpdateApplicationSuccess() throws Exception {
        UpdateApplicationServiceInput input = applicationData.updateApplicationServiceInput();
        ApplicationOutput output = applicationService.update(applicationData.getApplication1().id, input);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
        assertEquals(input.getDescription(), output.getDescription());
    }

    @Test
    public void testPartialUpdateApplicationSuccess() throws Exception {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setName("name only");
        ApplicationOutput output = applicationService.update(applicationData.getApplication1().id, input);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
    }

    @Test
    public void testUpdateThrowsException() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.update(999L, input));
    }

    @Test
    public void testUpdateApplicationInvalidCost() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setCostWithoutApp(new CreateCostWithoutApp(-1000.0, 500.0));

        assertThrows(InvalidCostException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input));
    }

    @Test
    public void testUpdatepplicationInvalidTechBusinessValue() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setTechBusinessValueWithoutApp(new CreateTechBusinessValueWithoutApp(5.5, 2.0));

        assertThrows(InvalidTechBusinessValueException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input));
    }

    @Test
    public void testCreateApplicationCategoryNotFound() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setCategoryId(9999L);

        assertThrows(CategoryODAChildNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input));
    }

    @Test
    public void testCreateApplicationDepartementNotFound() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setDepartementId(9999L);

        assertThrows(DepartementNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input));
    }

    @Test
    public void testCreateApplicationClasseNotFound() {
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setClasseId(9999L);

        assertThrows(ClasseNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input));
    }

}
