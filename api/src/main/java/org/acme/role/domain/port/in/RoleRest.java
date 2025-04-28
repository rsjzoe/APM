package org.acme.role.domain.port.in;

import java.util.List;

import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.model.input.HasAccess;
import org.acme.role.domain.model.input.UpdateRole;
import org.acme.role.domain.model.output.HasAccessOutput;

public interface RoleRest {
    public Role createRole(CreateRole role);

    public HasAccessOutput hasAccess(HasAccess params);

    public List<Role> findAll();

    public Role findByName(String roleName);

    public Role deleteByName(String roleName);

    public Role updateRole(Long id, UpdateRole role);
}
