package edu.uchicago.zachhuang4026.quarkus.Services;

import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Repositories.ObjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import java.util.ArrayList;
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

    public Object addIDDefault(Object object) {
//        Object checkDup = objectRepository.get(object.getId());
//
//        if (null != checkDup){
//            throw new NotSupportedException("The Object with id " + object.getId() + " already exists");
//        }
        return objectRepository.addIDDefault(object);
    }

    public List<Object> addMultiple(List<Object> objects) {
        List<Object> result = new ArrayList<>();
        for (Object object:objects) {
            result.add(add(object));
        }
        return result;
    }

    public Object get(String id) {
        Object object = objectRepository.get(id);
        if (null == object){
            throw new NotFoundException("The Object with id " + id + " was not found");
        }
        return object;
    }

    public List<Object> getAll() { return objectRepository.getAll(); }

    public List<Object> getFlaggedAll() { return objectRepository.getFlagedAll(); }

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

    public List<Object> filterName(String value) {
        return objectRepository.filterName(value);
    }

    public List<Object> filter(List<String> fields, List<String> filterValues) {
        return objectRepository.filter(fields, filterValues);
    }

    public List<Object> getMultiples(String[] ids) {
        return objectRepository.getMultiples(ids);
    }

}
