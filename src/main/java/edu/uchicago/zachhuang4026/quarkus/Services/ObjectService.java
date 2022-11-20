package edu.uchicago.zachhuang4026.quarkus.Services;

import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Repositories.ObjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import java.util.List;

@ApplicationScoped
public class ObjectService {
    @Inject
    ObjectRepository objectRepository;

    public Object add(Object object) {
        Object checkDup = objectRepository.get(object.getId());

        if (null != checkDup){
            throw new NotSupportedException("The Object with id " + object.getId() + " already exists");
        }
        return objectRepository.add(object);
    }

    public Object get(String id) {
        Object object = objectRepository.get(id);
        if (null == object){
            throw new NotFoundException("The Object with id " + id + " was not found");
        }
        return object;
    }

    public List<Object> getAll() { return objectRepository.getAll(); }

    public Object delete(String id) {
        Object item = objectRepository.get(id);
        if (null == item){
            throw new NotFoundException("The Object with id " + id + " was not found");
        }
        return objectRepository.delete(id);
    }

    public List<Object> paged(int page){
        return objectRepository.paged(page);
    }

    public Object update(String id, Object newObject) {
        Object checkDup = objectRepository.get(id);

        if (null == checkDup){
            throw new NotSupportedException("The Object with id " + id + " does not exists");
        }
        return objectRepository.update(id, newObject);
    }
}