package org.acme.documentation.adapter.out;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.DocumentationType;
import org.acme.documentation.domain.input.CreateDocumentation;
import org.acme.documentation.domain.input.UpdateDocumentation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class DocumentationEntity extends PanacheEntity {
    private String name;
    private String url;
    private DocumentationType type;
    private String filename;
    @ManyToOne
    private ApplicationEntity applicationEntity;

    public DocumentationEntity() {
    }

    public DocumentationEntity(CreateDocumentation create) {
        this.name = create.getName();
        this.url = create.getUrl();
        this.type = create.getType();
        this.filename = create.getFilename();
        this.applicationEntity = ApplicationEntityHelper.entityFromId(create.getApplicationId());
    }

    public DocumentationEntity(String name, String url, Long applicationId, DocumentationType type) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.applicationEntity = ApplicationEntityHelper.entityFromId(applicationId);
    }

    public DocumentationEntity(Long id, String name, String url, Long applicationId, DocumentationType type) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.applicationEntity = ApplicationEntityHelper.entityFromId(applicationId);

    }

    public Documentation toDocumentation() {
        return new Documentation(id, name, filename, url, type);
    }

    public DocumentationEntity updateFrom(UpdateDocumentation update) {
        this.name = update.getName();
        this.type = update.getType();
        this.url = update.getUrl();
        return this;
    }

    public DocumentationType getType() {
        return type;
    }

    public void setType(DocumentationType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ApplicationEntity getApplicationEntity() {
        return applicationEntity;
    }

    public void setApplicationEntity(ApplicationEntity applicationEntity) {
        this.applicationEntity = applicationEntity;
    }

    @Override
    public String toString() {
        return "DocumentationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
