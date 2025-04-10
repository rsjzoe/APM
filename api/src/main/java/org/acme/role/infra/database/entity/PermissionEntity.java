package org.acme.role.infra.database.entity;

import org.acme.role.domain.input.CreatePermission;
import org.acme.role.domain.model.Permission;

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
public class PermissionEntity extends PanacheEntity {
    public Boolean canUpdate;
    public Boolean canDelete;
    public Boolean canRead;
    public Boolean canCreate;
    public ServiceEntity service;

    public PermissionEntity(CreatePermission createPermission) {
        this.canUpdate = createPermission.canUpdate;
        this.canDelete = createPermission.canDelete;
        this.canRead = createPermission.canRead;
        this.canCreate = createPermission.canCreate;
    }

    public Permission toPermission() {
        return new Permission(this.id, this.canUpdate, this.canDelete, this.canRead, this.canCreate,
                service.toService());
    }
}
