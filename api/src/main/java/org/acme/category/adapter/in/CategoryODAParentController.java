package org.acme.category.adapter.in;

import java.util.List;

import org.acme.category.domain.exception.CategoryODAParentNotFoundException;
import org.acme.category.domain.input.*;
import org.acme.category.domain.output.CategoryODAParentOutput;
import org.acme.category.domain.port.in.CategoryODAParentRest;
import org.acme.roleGuard.RoleAllowedCustom;
import org.acme.category.app.CategoryODAParentService;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;

@Path("/category-oda-parent")
@RoleAllowedCustom({ "admin" })
public class CategoryODAParentController implements CategoryODAParentRest {

    @Inject
    CategoryODAParentService categoryODAParentUsecase;

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
        try {
            return categoryODAParentUsecase.findById(id);
        } catch (CategoryODAParentNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public CategoryODAParentOutput deleteById(@PathParam("id") Long id) {
        try {
            return categoryODAParentUsecase.deleteById(id);
        } catch (CategoryODAParentNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @PUT
    @Path("/{id}")
    public CategoryODAParentOutput updateById(@PathParam("id") Long id, UpdateCategoryODAParent categoryParent) {
        try {
            return categoryODAParentUsecase.updateById(id, categoryParent);
        } catch (CategoryODAParentNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}