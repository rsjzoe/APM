package org.acme.role.infra.controller;

import java.util.List;

import org.acme.role.app.RoleService;
import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.port.in.RoleRest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("role")
public class RoleController implements RoleRest {
    @Inject
    RoleService roleService;

    @POST
    @Override
    public Role createRole(CreateRole role) {
        return roleService.createRole(role);
    }

    @Override
    @GET
    public List<Role> findAll() {
        return roleService.findAll();
    }

}
