package org.acme.departement.infra.in;

import java.util.List;

import org.acme.departement.app.DepartementService;
import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.domain.port.in.DepartementRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/departements")
public class DepartementController implements DepartementRest {
    @Inject
    DepartementService departementUseCase;

    @Transactional
    @GET
    public List<Departement> listDepartement() {
        return departementUseCase.listDepartement();
    }

    @GET
    @Path("/{id}")
    public Departement findByDepartementId(@PathParam("id") Long id) {
        try {
            return departementUseCase.findByDepartementId(id);
        } catch (DepartementNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
