package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import edu.uchicago.zachhuang4026.quarkus.Models.Result;
import edu.uchicago.zachhuang4026.quarkus.Services.ItemService;
import edu.uchicago.zachhuang4026.quarkus.Services.PeopleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {
    @Inject
    PeopleService peopleService;

    @POST
    public Result add(Result result) {
        return peopleService.add(result);
    }

    @GET
    @Path("/{id}")
    public Result get(@PathParam("id") String id) {
        return peopleService.get(id);
    }

    @GET
    public String getAll() {
        return peopleService.getAll();
    }

    @DELETE
    @Path("/{id}")
    public Result delete(@PathParam("id") String id) {
        return peopleService.delete(id);
    }


    @GET
    @Path("/paged/{page}")
    public List<Result> paged(@PathParam("page") int page){
        return peopleService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Result update(@PathParam("id") String id, Result newResult){
        return peopleService.update(id, newResult);
    }
}
