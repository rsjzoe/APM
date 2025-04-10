package org.acme.role.domain.port.out;

import java.util.List;

import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;

public interface RoleRepository {
    public Role createRole(CreateRole role);

    public List<Role> findAll();
}
