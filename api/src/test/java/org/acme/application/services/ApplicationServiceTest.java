package org.acme.application.services;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;

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

}
