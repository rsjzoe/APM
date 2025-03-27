package org.acme.application.domain.input;

import org.acme.application.domain.output.ApplicationOutput;

public class CreateApplicationHistoryService {
    private ApplicationOutput app;
    private String appString;

    public CreateApplicationHistoryService(ApplicationOutput app, String appString) {
        this.app = app;
        this.appString = appString;
    }

    public ApplicationOutput getApp() {
        return app;
    }

    public void setApp(ApplicationOutput app) {
        this.app = app;
    }

    public String getAppString() {
        return appString;
    }
}
