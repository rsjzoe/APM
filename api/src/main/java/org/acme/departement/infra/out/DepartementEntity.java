package org.acme.departement.infra.out;

import org.acme.departement.domain.Departement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class DepartementEntity extends PanacheEntity{
    public String name;

    public DepartementEntity() {
    }

    public DepartementEntity(Long id, String name) {
        this.name = name;
    }

    public Departement toDepartement() {
        return new Departement(id, name);
    }
}
