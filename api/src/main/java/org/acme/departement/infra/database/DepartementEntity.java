package org.acme.departement.infra.database;

import java.util.ArrayList;
import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.departement.domain.Departement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class DepartementEntity extends PanacheEntity {
    public String name;
    @ManyToMany(mappedBy = "departements")
    private List<ApplicationEntity> applications = new ArrayList<>();

    public DepartementEntity() {
    }

    public DepartementEntity(Long id, String name) {
        this.name = name;
    }

    public Departement toDepartement() {
        return new Departement(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
