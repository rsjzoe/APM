package org.acme.category.adapters.in;

import java.util.List;

import org.acme.category.domain.input.CreateCategoryODAChild;
import org.acme.category.domain.input.UpdateCategoryODAChild;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.category.ports.in.CategoryODAChildRest;
import org.acme.roleGuard.RoleAllowedCustom;
// import org.acme.category.ports.CategoryODAChildService;
import org.acme.category.app.CategoryODAChildUseCase;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.inject.Inject;

@Path("/category-oda-child")
@RoleAllowedCustom({ "admin" })
public class CategoryODAChildController implements CategoryODAChildRest {

    @Inject
    CategoryODAChildUseCase categoryODAChildUsecase;

    @POST
    public CategoryODAChildOutput save(CreateCategoryODAChild categoryChild) {
        return categoryODAChildUsecase.save(categoryChild);
    }

    @GET
    public List<CategoryODAChildOutput> findAll() {
        return categoryODAChildUsecase.findAll();
    }

    @GET
    @Path("/{id}")
    public CategoryODAChildOutput findById(@PathParam("id") Long id) {
        return categoryODAChildUsecase.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public CategoryODAChildOutput deleteById(@PathParam("id") Long id) {
        return categoryODAChildUsecase.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    public CategoryODAChildOutput updateById(@PathParam("id") Long id, UpdateCategoryODAChild categoryParent) {
        return categoryODAChildUsecase.updateById(id, categoryParent);
    }
}