package org.acme.role.app;

import java.util.List;

import org.acme.role.domain.exception.ConflitRoleException;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.ActionType;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.model.input.HasAccess;
import org.acme.role.domain.model.output.HasAccessOutput;
import org.acme.role.domain.port.out.RoleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoleService {
    @Inject
    RoleRepository roleRepository;

    public Role createRole(CreateRole role) throws ConflitRoleException {
        try {
            roleRepository.findRoleByName(role.getRoleName());
            throw new ConflitRoleException();

        } catch (RoleNotFoundException e) {
            role.correctRole();
            return roleRepository.createRole(role);
        }

    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public HasAccessOutput hasAccess(HasAccess params) throws RoleNotFoundException {
        // ilay role name ve manana acces anao an ilay action amle service
        Role data = roleRepository.findRoleByName(params.getRoleName());
        for (var permission : data.getPermissions()) {
            if (permission.getService().getName().equals(params.getServiceName())) {
                // raha cancreate angathan ilay parametre de tokony manana cancreate koa le
                // permission
                if (params.getAction() == ActionType.canCreate && permission.getCanCreate()) {
                    return HasAccessOutput.ok();
                }

                if (params.getAction() == ActionType.canUpdate && permission.getCanUpdate()) {
                    return HasAccessOutput.ok();
                }

                if (params.getAction() == ActionType.canDelete && permission.getCanDelete()) {
                    return HasAccessOutput.ok();
                }

                if (params.getAction() == ActionType.canRead && permission.getCanRead()) {
                    return HasAccessOutput.ok();
                }

            }
        }
        return HasAccessOutput.notOk();
    }

    public Role deleteByName(String roleName) throws RoleNotFoundException {
        return roleRepository.deleteByName(roleName);
    }
}
