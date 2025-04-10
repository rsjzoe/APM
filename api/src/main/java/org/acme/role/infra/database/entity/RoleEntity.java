package org.acme.role.infra.database.entity;

import java.util.List;

import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;

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
public class RoleEntity extends PanacheEntity {
    public String roleName;
    public List<PermissionEntity> permissions;

    public RoleEntity(CreateRole createRole) {
        this.roleName = createRole.roleName;
        this.permissions = createRole.permissions.stream().map(PermissionEntity::new).toList();
    }

    public Role toRole() {
        return new Role(this.id, this.roleName, this.permissions.stream().map(PermissionEntity::toPermission).toList());
    }
}
