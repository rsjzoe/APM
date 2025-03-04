package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;

public class UpdateDocumentation {
    private String name;
    private String url;
    private DocumentationType type;

    public UpdateDocumentation() {
    }

    public UpdateDocumentation(String name, String url, DocumentationType type) {
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UpdateDocumentation{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
