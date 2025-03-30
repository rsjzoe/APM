package org.acme.classe.infra.controller;

import java.util.List;

import org.acme.classe.app.ClasseService;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.domain.port.in.ClasseRest;
import org.acme.roleGuard.RoleAllowedCustom;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/classe")
@Authenticated
public class ClasseController implements ClasseRest {
    @Inject
    ClasseService classeService;

    @Override
    @GET
    public List<ClasseOutput> getListAll() {
        return classeService.getListAll();
    }

    @Override
    @POST
    @RoleAllowedCustom({ "admin" })
    public ClasseOutput create(CreateClasseInput classe) {
        return classeService.create(classe);
    }

    @Override
    @Path("/{id}")
    @PUT
    @RoleAllowedCustom({ "admin" })
    public ClasseOutput update(@PathParam("id") Long id, UpdateClasse classe) {
        try {
            return classeService.update(id, classe);
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    @GET
    @Path("/{id}")
    @RoleAllowedCustom({ "admin" })
    public ClasseOutput findById(@PathParam("id") Long id) {
        try {
            return classeService.findById(id);
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @DELETE
    @Path("/{id}")
    @RoleAllowedCustom({ "admin" })
    public ClasseOutput deleteById(@PathParam("id") Long id) {
        try {
            return classeService.deleteById(id);
        } catch (ClasseNotFoundException e) {
            throw new NotFoundException();
        }
    }

}
