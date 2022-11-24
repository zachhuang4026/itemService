package edu.uchicago.zachhuang4026.quarkus.Models;

import java.io.Serializable;

public class Object implements Serializable {
    private String id;
    private String name;
    private String description;
    private String categoryID;
    private String categoryName;
    private int quantity;
    private int bidPrice;
    private boolean isSold;
    private boolean isAppropriate;

    // auction_id

    public Object(String id, String name, String description, String categoryID, String categoryName, int quantity, int bidPrice, boolean isSold, boolean isAppropriate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.bidPrice = bidPrice;
        this.isSold = isSold;
        this.isAppropriate = isAppropriate;
    }
    public Object(){};

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public boolean isSold() {
        return isSold;
    }

    public boolean isAppropriate() {
        return isAppropriate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public void setAppropriate(boolean appropriate) {
        isAppropriate = appropriate;
    }

    @Override
    public String toString() {
        return "Object{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                '}';
    }
}
