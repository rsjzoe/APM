package org.acme.role.domain.port.out;

import java.util.List;

import org.acme.role.domain.exception.RoleActif;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.model.input.UpdateRole;

public interface RoleRepository {
    public Role createRole(CreateRole role);

    public Role findRoleByName(String roleName) throws RoleNotFoundException;

    public List<Role> findAll();

    public Role deleteByName(String roleName) throws RoleNotFoundException, RoleActif;

    public Role updateRole(Long id, UpdateRole role) throws RoleNotFoundException;
}
