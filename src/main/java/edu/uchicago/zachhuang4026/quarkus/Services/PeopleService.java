package edu.uchicago.zachhuang4026.quarkus.Services;


import edu.uchicago.zachhuang4026.quarkus.Models.Result;
import edu.uchicago.zachhuang4026.quarkus.Repositories.ItemRepository;
import edu.uchicago.zachhuang4026.quarkus.Repositories.PeopleRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import java.util.List;

@ApplicationScoped
public class PeopleService {
    @Inject
    PeopleRepository peopleRepository;

    public Result add(Result result) {
        //check for dup before adding record.
        Result checkDup = peopleRepository.get(result.getId());
        if (null != checkDup){
            throw new NotSupportedException("The People with id " + result.getId() + " already exists");
        }
        return peopleRepository.add(result);
    }

    public Result get(String id) {
        Result item = peopleRepository.get(id);
        if (null == item){
            throw new NotFoundException("The People with id " + id + " was not found");
        }
        return item;
    }

    public String getAll() {
        return peopleRepository.getAll();
    }

    public Result delete(String id) {
        Result item = peopleRepository.get(id);
        if (null == item){
            throw new NotFoundException("The People with id " + id + " was not found");
        }
        return peopleRepository.delete(id);
    }

    public List<Result> paged(int page){
        return peopleRepository.paged(page);
    }

    public Result update(String id, Result newResult) {
        Result checkDup = peopleRepository.get(id);
        if (null == checkDup){
            throw new NotSupportedException("The People with id " + id + " does not exists");
        }
        return peopleRepository.update(id, newResult);
    }
}
