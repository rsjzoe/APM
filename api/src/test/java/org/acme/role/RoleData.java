package org.acme.role;

import org.acme.role.app.RoleService;
import org.acme.role.domain.exception.ConflitRoleException;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.model.input.CreatePermission;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ApplicationScoped
@Getter
public class RoleData {
    @Inject
    RoleService roleService;

    @Inject
    ServiceData serviceData;

    private Role apmsuperadmin;
    private Role readAppOnly;

    public void setup() {
        serviceData.setup();
        // apmsuperadmin
        CreateRole superAdminRole = new CreateRole();
        superAdminRole.setRoleName("apmsuperadmin");

        var permissions = serviceData.getServices().stream().map(service -> {
            CreatePermission permission = new CreatePermission();
            permission.setServiceId(service.getId());
            permission.setCanCreate(true);
            permission.setCanRead(true);
            permission.setCanUpdate(true);
            permission.setCanDelete(true);
            return permission;
        }).toList();

        superAdminRole.setPermissions(permissions);

        try {
            apmsuperadmin = roleService.createRole(superAdminRole);
        } catch (ConflitRoleException e) {
        }

        // readAppOnly
        CreateRole readApp = new CreateRole();
        readApp.setRoleName("readAppOnly");

        var readAppPermissions = serviceData.getServices().stream().map(service -> {
            CreatePermission permission = new CreatePermission();
            permission.setServiceId(service.getId());
            if (service.getName() == "application") {
                permission.setCanCreate(true);
            } else {
                permission.setCanCreate(false);

            }
            permission.setCanRead(false);
            permission.setCanUpdate(false);
            permission.setCanDelete(false);
            return permission;
        }).toList();

        readApp.setPermissions(readAppPermissions);

        try {
            readAppOnly = roleService.createRole(readApp);
        } catch (ConflitRoleException e) {
        }
    }

    public void clear() {
        delete(apmsuperadmin.getRoleName());
        delete(readAppOnly.getRoleName());
    }

    private void delete(String rolename) {
        try {
            roleService.deleteByName(rolename);
        } catch (RoleNotFoundException e) {
        }
    }
}
