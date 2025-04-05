package org.acme.departement.infra.controller;

import java.util.List;

import org.acme.departement.app.DepartementService;
import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.domain.port.in.DepartementRest;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/departements")
@Authenticated
public class DepartementController implements DepartementRest {
    @Inject
    DepartementService departementService;

    @Transactional
    @GET
    public List<Departement> listDepartement() {
        return departementService.listDepartement();
    }

    @GET
    @Path("/{id}")
    public Departement findByDepartementId(@PathParam("id") Long id) {
        try {
            return departementService.findByDepartementId(id);
        } catch (DepartementNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
