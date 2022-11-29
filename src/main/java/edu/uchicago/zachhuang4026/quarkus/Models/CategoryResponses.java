package edu.uchicago.zachhuang4026.quarkus.Models;

import java.util.List;

public class CategoryResponses {
    private String statueCode;
    private List<Category> categories;

    public CategoryResponses(String statueCode, List<Category> categories) {
        this.statueCode = statueCode;
        this.categories = categories;
    }

    public CategoryResponses() {
    }

    public String getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(String statueCode) {
        this.statueCode = statueCode;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
