package org.acme.classe.infra.database;

import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class ClasseEntity extends PanacheEntity {
    public String name;
    public String description;
    @OneToMany
    public List<ApplicationEntity> application;
    public boolean isDeleted;

    public ClasseEntity() {
    }

    public ClasseEntity(String name, String description, List<ApplicationEntity> application) {
        this.name = name;
        this.description = description;
        this.application = application;
    }

    public ClasseEntity(CreateClasseInput data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.isDeleted = false;
    }

    public ClasseOutput toOutput() {
        return new ClasseOutput(id, name, description, isDeleted);
    }

    public void update(UpdateClasse data) {
        this.name = data.getName();
        this.description = data.getDescription();

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ApplicationEntity> getApplication() {
        return application;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApplication(List<ApplicationEntity> application) {
        this.application = application;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
