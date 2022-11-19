package edu.uchicago.zachhuang4026.quarkus.Resources;

import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import edu.uchicago.zachhuang4026.quarkus.Services.ItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {
    @Inject
    ItemService itemService;

    @POST
    public Item add(Item item) {
        return itemService.add(item);
    }

    @GET
    @Path("/{id}")
    public Item get(@PathParam("id") String id) {
        return itemService.get(id);
    }

    @GET
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @DELETE
    @Path("/{id}")
    public Item delete(@PathParam("id") String id) {
        return itemService.delete(id);
    }


    @GET
    @Path("/paged/{page}")
    public List<Item> paged(@PathParam("page") int page){
        return  itemService.paged(page);
    }

    @PUT
    @Path("update/{id}")
    public Item update(@PathParam("id") String id, Item newItem){
        return itemService.update(id, newItem);
    }
}
