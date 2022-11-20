package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Services.ObjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/objects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObjectResources {

    @Inject
    ObjectService objectService;

    @POST
    public Object add(Object object) {
        return objectService.add(object);
    }

    @GET
    @Path("/{id}")
    public Object get(@PathParam("id") String id) {
        return objectService.get(id);
    }

    @GET
    public List<Object> getAll() { return objectService.getAll(); }

    @DELETE
    @Path("/{id}")
    public Object delete(@PathParam("id") String id) {
        return objectService.delete(id);
    }

    @GET
    @Path("/paged/{page}")
    public List<Object> paged(@PathParam("page") int page) {
        return objectService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Object update(@PathParam("id") String id, Object newObject) {
        return objectService.update(id, newObject);
    }
}
