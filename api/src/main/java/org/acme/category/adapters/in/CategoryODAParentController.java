package org.acme.category.adapters.in;

import java.util.List;

import org.acme.category.domain.input.*;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.ports.in.CategoryODAParentRest;
import org.acme.roleGuard.RoleAllowedCustom;
import org.acme.category.app.CategoryODAParentUseCase;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;

@Path("/category-oda-parent")
@RoleAllowedCustom({"admin"})
public class CategoryODAParentController implements CategoryODAParentRest {

    @Inject
    CategoryODAParentUseCase categoryODAParentUsecase;

    @POST
    public CategoryODAParentOutput save(CreateCategoryODAParent categoryParent) {
        return categoryODAParentUsecase.save(categoryParent);
    }

    @GET
    public List<CategoryODAParentOutput> findAll() {
        return categoryODAParentUsecase.findAll();
    }

    @GET
    @Path("/{id}")
    public CategoryODAParentOutput findById(@PathParam("id") Long id) {
        return categoryODAParentUsecase.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public CategoryODAParentOutput deleteById(@PathParam("id") Long id) {
        return categoryODAParentUsecase.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    public CategoryODAParentOutput updateById(@PathParam("id") Long id, UpdateCategoryODAParent categoryParent) {
        return categoryODAParentUsecase.updateById(id, categoryParent);
    }

}