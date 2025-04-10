package org.acme.role.domain.port.in;

import java.util.List;

import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.input.HasAccess;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.output.HasAccessOutput;

public interface RoleRest {
    public Role createRole(CreateRole role);

    public HasAccessOutput hasAccess(HasAccess params);

    public List<Role> findAll();
}
