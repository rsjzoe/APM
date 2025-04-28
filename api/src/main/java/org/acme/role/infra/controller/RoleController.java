package org.acme.role.infra.controller;

import java.util.List;

import org.acme.role.app.RoleService;
import org.acme.role.domain.exception.ConflitRoleException;
import org.acme.role.domain.exception.RoleActif;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.model.input.CreateRole;
import org.acme.role.domain.model.input.HasAccess;
import org.acme.role.domain.model.input.UpdateRole;
import org.acme.role.domain.model.output.HasAccessOutput;
import org.acme.role.domain.port.in.RoleRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("role")
public class RoleController implements RoleRest {
    @Inject
    RoleService roleService;

    @POST
    @Override
    @Transactional
    public Role createRole(CreateRole role) {
        try {
            return roleService.createRole(role);
        } catch (ConflitRoleException e) {
            System.out.println("conglittt");
            throw new BadRequestException();
        }
    }

    @Override
    @GET
    @Transactional
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @Override
    @GET
    @Path("/has-access")
    @Transactional
    public HasAccessOutput hasAccess(@BeanParam HasAccess params) {
        try {
            return roleService.hasAccess(params);
        } catch (RoleNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @DELETE
    @Path("{roleName}")
    public Role deleteByName(@PathParam("roleName") String roleName) {
        try {
            return roleService.deleteByName(roleName);
        } catch (RoleNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (RoleActif e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @Path("{id}")
    @PUT
    public Role updateRole(@PathParam("id") Long id, UpdateRole role) {
        try {
            return roleService.updateRole(id, role);
        } catch (RoleNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

}
