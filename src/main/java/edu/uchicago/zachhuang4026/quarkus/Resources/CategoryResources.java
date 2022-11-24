package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.Category;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Services.CategoryService;
import edu.uchicago.zachhuang4026.quarkus.Services.ObjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResources {
    @Inject
    CategoryService categoryService;

    @POST
    public Category add(Category category) {
        return categoryService.add(category);
    }

    @GET
    @Path("/{id}")
    public Category get(@PathParam("id") String id) {
        return categoryService.get(id);
    }

    @GET
    public List<Category> getAll() { return categoryService.getAll(); }

    @DELETE
    @Path("/{id}")
    public Category delete(@PathParam("id") String id) {
        return categoryService.delete(id);
    }

    @GET
    @Path("/paged/{page}")
    public List<Category> paged(@PathParam("page") int page) {
        return categoryService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Category update(@PathParam("id") String id, Category newCategory) {
        return categoryService.update(id, newCategory);
    }
}
