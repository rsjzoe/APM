package org.acme.role.infra.database.entity;

import org.acme.role.domain.model.Service;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceEntity extends PanacheEntity {
    public String name;

    public Service toService() {
        return new Service(id, name);
    }
}
