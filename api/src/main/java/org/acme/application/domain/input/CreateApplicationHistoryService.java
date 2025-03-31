package org.acme.application.domain.input;

import org.acme.application.domain.output.ApplicationOutput;

public class CreateApplicationHistoryService {
    private ApplicationOutput app;
    private String token;

    public CreateApplicationHistoryService(ApplicationOutput app, String token) {
        this.app = app;
        this.token = token;
    }

    public ApplicationOutput getApp() {
        return app;
    }

    public void setApp(ApplicationOutput app) {
        this.app = app;
    }

    public String getToken() {
        return token;
    }
}
