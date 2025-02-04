package org.acme.category.infra.in;

import java.util.List;

import org.acme.category.app.CategoryService;
import org.acme.category.domain.Category;
import org.acme.category.domain.port.in.CategoryRest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/category")
public class CategoryController implements CategoryRest {
    @Inject
    CategoryService categoryService;

    @Override
    @GET
    public List<Category> listAll() {
        return categoryService.listAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Category findById(@PathParam("id") int id) {
        return categoryService.findById(id);
    }

}
