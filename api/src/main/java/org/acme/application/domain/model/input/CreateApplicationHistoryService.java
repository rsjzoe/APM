package org.acme.application.domain.model.input;

public class CreateApplicationHistoryService {
    private Long appId;

    public CreateApplicationHistoryService(Long appId) {
        this.appId = appId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
