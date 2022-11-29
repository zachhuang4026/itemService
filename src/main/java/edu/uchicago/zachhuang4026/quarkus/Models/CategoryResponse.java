package edu.uchicago.zachhuang4026.quarkus.Models;

public class CategoryResponse {
    private String statueCode;
    private Category category;

    public CategoryResponse(String statueCode, Category category) {
        this.statueCode = statueCode;
        this.category = category;
    }

    public CategoryResponse() {
    }

    public String getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(String statueCode) {
        this.statueCode = statueCode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
