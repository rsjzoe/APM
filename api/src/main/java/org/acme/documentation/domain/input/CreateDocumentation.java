package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;

public class CreateDocumentation {
    private String name;
    private String filename;
    private String url;
    private DocumentationType type;
    private Long applicationId;

    public CreateDocumentation() {
    }

    public CreateDocumentation(String name, String filename, String url, DocumentationType type, Long applicationId) {
        this.name = name;
        this.filename = filename;
        this.url = url;
        this.type = type;
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DocumentationType getType() {
        return type;
    }

    public void setType(DocumentationType type) {
        this.type = type;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "CreateDocumentation{" +
                "name='" + name + '\'' +
                ", filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", applicationId=" + applicationId +
                '}';
    }
}
