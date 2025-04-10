package org.acme.role.infra.database.repository;

import java.util.List;

import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.port.out.RoleRepository;
import org.acme.role.infra.database.entity.RoleEntity;

public class RoleEntityRepository implements RoleRepository {

    @Override
    public Role createRole(CreateRole role) {
        RoleEntity roleEntity = new RoleEntity(role);
        roleEntity.persist();
        return roleEntity.toRole();
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> data = RoleEntity.listAll();
        return data.stream()
                .map(RoleEntity::toRole)
                .toList();
    }

    @Override
    public Role findRoleByName(String roleName) throws RoleNotFoundException {
        RoleEntity roleEntity = RoleEntity.find("roleName", roleName).firstResult();
        if (roleEntity == null) {
            throw new RoleNotFoundException(roleName);
        }
        return roleEntity.toRole();
    }

}
