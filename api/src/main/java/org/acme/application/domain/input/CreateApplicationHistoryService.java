package org.acme.application.domain.input;

import org.acme.application.domain.output.ApplicationOutput;

public class CreateApplicationHistoryService {
    private ApplicationOutput app;

    public CreateApplicationHistoryService(ApplicationOutput app) {
        this.app = app;
    }

    public ApplicationOutput getApp() {
        return app;
    }

    public void setApp(ApplicationOutput app) {
        this.app = app;
    }
}
