package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.ItemResponse;
import edu.uchicago.zachhuang4026.quarkus.Models.ItemResponses;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Services.ObjectService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/objects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObjectResources {

    @Inject
    ObjectService objectService;

    @POST
    public Response add(Object object) {
        Object newObject;

        try {
            newObject = objectService.add(object);
        } catch (Exception e) {
            ItemResponse errorResponse = new ItemResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        ItemResponse successResponse = new ItemResponse("200", newObject);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        Object object;

        try {
            object = objectService.get(id);
        } catch (Exception e) {
            ItemResponse errorResponse = new ItemResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        ItemResponse successResponse = new ItemResponse("200", object);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    public Response getAll() {
        List<Object> objects;

        try {
            objects = objectService.getAll();
        } catch (Exception e) {
            ItemResponses errorResponse = new ItemResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        ItemResponses successResponse = new ItemResponses("200", objects);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/flagged")
    public Response getFlaggedAll() {
        List<Object> objects;

        try {
            objects = objectService.getFlaggedAll();
        } catch (Exception e) {
            ItemResponses errorResponse = new ItemResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        ItemResponses successResponse = new ItemResponses("200", objects);
        return Response.ok().entity(successResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Object object;

        try {
            object = objectService.delete(id);
        } catch (Exception e) {
            ItemResponse errorResponse = new ItemResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        ItemResponse successResponse = new ItemResponse("200", object);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/paged/{page}")
    public List<Object> paged(@PathParam("page") int page) {
        return objectService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Response update(@PathParam("id") String id, Object newObject) {
        Object object;

        try {
            object = objectService.update(id, newObject);
        } catch (Exception e) {
            ItemResponse errorResponse = new ItemResponse("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        ItemResponse successResponse = new ItemResponse("200", object);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/filter/{fields}")
    public Response filter(@PathParam("fields") String fields) {
        List<Object> objects;
        String[] parsedFields = fields.split(",");

        List<String> fieldArray = new ArrayList<>();
        List<String> valueArray = new ArrayList<>();

        for (String parsedField:parsedFields) {
            String field = parsedField.split("=")[0];
            String value = parsedField.split("=")[1];
            fieldArray.add(field);
            valueArray.add(value);
        }

        try {
            objects = objectService.filter(fieldArray, valueArray);
        } catch (Exception e) {
            ItemResponses errorResponse = new ItemResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        ItemResponses successResponse = new ItemResponses("200", objects);
        return Response.ok().entity(successResponse).build();

    }

    @GET
    @Path("/multiple/{ids}")
    public Response getMultiple(@PathParam("ids") String ids) {

        String[] idArray = ids.split(",");

        List<Object> objects = new ArrayList<>();

        try {
            for (String id:idArray) {
                objects.add(objectService.get(id));
            }
        } catch (Exception e) {
            ItemResponses errorResponse = new ItemResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }
        ItemResponses successResponse = new ItemResponses("200", objects);
        return Response.ok().entity(successResponse).build();
    }

    // not working now
    @GET
    @Path("/multiples/{ids}")
    public Response getMultiples(@PathParam("ids") String ids) {
        String[] parsedFields = ids.split(",");
        List<Object> objects;

        try {
            objects = objectService.getMultiples(parsedFields);
        } catch (Exception e) {
            ItemResponses errorResponse = new ItemResponses("204", null);
            return Response.status(Response.Status.NO_CONTENT).entity(errorResponse).build();
        }

        ItemResponses successResponse = new ItemResponses("200", objects);
        return Response.ok().entity(successResponse).build();
    }

    @GET
    @Path("/check")
    public Response sanityCheck() {
        return Response.ok().entity("200, Item Service Running").build();
    }
}
