package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.*;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Services.CategoryService;
import edu.uchicago.zachhuang4026.quarkus.Services.ObjectService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
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
    public Response add(Category category) {
        Category newCategory;

        try {
            newCategory = categoryService.addIDDefault(category);
        }  catch (Exception e) {
            CategoryResponse errorResponse = new CategoryResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        CategoryResponse successResponse = new CategoryResponse("200", newCategory);
        return Response.ok().entity(successResponse).build();
    }
    @POST
    @Path("/multiple")
    public Response addMultiple(List<Category> categories) {
        List<Category> newCategories;

        try {
            newCategories = categoryService.addMultiple(categories);
        } catch (Exception e) {
            ItemResponse errorResponse = new ItemResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        CategoryResponses successResponse = new CategoryResponses("200", newCategories);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        Category category;
        try {
            category = categoryService.get(id);
        }  catch (Exception e) {
            CategoryResponse errorResponse = new CategoryResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        CategoryResponse successResponse = new CategoryResponse("200", category);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    public Response getAll() {
        List<Category> categories;

        try {
            categories = categoryService.getAll();
        } catch (Exception e) {
            CategoryResponses errorResponse = new CategoryResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        CategoryResponses successResponse = new CategoryResponses("200", categories);
        return Response.ok().entity(successResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Category category;

        try {
            category = categoryService.delete(id);
        } catch (Exception e) {
            CategoryResponse errorResponse = new CategoryResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        CategoryResponse successResponse = new CategoryResponse("200", category);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/paged/{page}")
    public List<Category> paged(@PathParam("page") int page) {
        return categoryService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Response update(@PathParam("id") String id, Category newCategory) {
        Category category;

        try {
            category = categoryService.update(id, newCategory);
        } catch (Exception e) {
            CategoryResponse errorResponse = new CategoryResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        CategoryResponse successResponse = new CategoryResponse("200", category);
        return Response.ok().entity(successResponse).build();
    }
}
