package org.acme.role.domain.port.in;

import java.util.List;

import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;

public interface RoleRest {
    public Role createRole(CreateRole role);

    public List<Role> findAll();
}
