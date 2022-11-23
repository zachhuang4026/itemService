package edu.uchicago.zachhuang4026.quarkus.Services;

import edu.uchicago.zachhuang4026.quarkus.Models.Category;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import edu.uchicago.zachhuang4026.quarkus.Repositories.CategoryRepository;
import edu.uchicago.zachhuang4026.quarkus.Repositories.ObjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import java.util.List;

@ApplicationScoped
public class CategoryService {
    @Inject
    CategoryRepository categoryRepository;

    public Category add(Category category) {
        Category checkDup = categoryRepository.get(category.getId());

        if (null != checkDup){
            throw new NotSupportedException("The category with id " + category.getId() + " already exists");
        }
        return categoryRepository.add(category);
    }

    public Category get(String id) {
        Category category = categoryRepository.get(id);
        if (null == category){
            throw new NotFoundException("The category with id " + id + " was not found");
        }
        return category;
    }

    public List<Category> getAll() { return categoryRepository.getAll(); }

    public Category delete(String id) {
        Category category = categoryRepository.get(id);
        if (null == category){
            throw new NotFoundException("The Object with id " + id + " was not found");
        }
        return categoryRepository.delete(id);
    }

    public List<Category> paged(int page){
        return categoryRepository.paged(page);
    }

    public Category update(String id, Category newCategory) {
        Category checkDup = categoryRepository.get(id);

        if (null == checkDup){
            throw new NotSupportedException("The Object with id " + id + " does not exists");
        }
        return categoryRepository.update(id, newCategory);
    }
}
