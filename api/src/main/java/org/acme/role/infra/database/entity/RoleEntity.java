package org.acme.role.infra.database.entity;

import java.util.List;

import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    @OneToMany(cascade = CascadeType.ALL)
    public List<PermissionEntity> permissions;

    public RoleEntity(CreateRole createRole) {
        this.roleName = createRole.getRoleName();
        this.permissions = createRole.getPermissions().stream().map(PermissionEntity::new).toList();
    }

    public Role toRole() {
        return new Role(this.id, this.roleName, this.permissions.stream().map(PermissionEntity::toPermission).toList());
    }
}
