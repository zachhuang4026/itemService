package edu.uchicago.zachhuang4026.quarkus.Services;


import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import edu.uchicago.zachhuang4026.quarkus.Repositories.ItemRepository;
import software.amazon.awssdk.services.dynamodb.model.ItemResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import java.util.List;

@ApplicationScoped
public class ItemService {
    @Inject
    ItemRepository itemRepository;

    public Item add(Item item) {
        //check for dup before adding record.
        Item checkDup = itemRepository.get(item.getId());
        if (null != checkDup){
            throw new NotSupportedException("The Item with id " +item.getId() + " already exists");
        }
        return itemRepository.add(item);
    }

    public Item get(String id) {
        Item item = itemRepository.get(id);
        if (null == item){
            throw new NotFoundException("The Item with id " + id + " was not found");
        }
        return item;
    }

    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    public Item delete(String id) {
        Item item = itemRepository.get(id);
        if (null == item){
            throw new NotFoundException("The Item with id " + id + " was not found");
        }
        return itemRepository.delete(id);
    }

    public List<Item> paged(int page){
        return itemRepository.paged(page);
    }

    public Item update(String id, Item newItem) {
        Item checkDup = itemRepository.get(id);
        if (null == checkDup){
            throw new NotSupportedException("The Item with id " + id + " does not exists");
        }
        return itemRepository.update(id, newItem);
    }
}
