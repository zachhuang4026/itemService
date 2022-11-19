package edu.uchicago.zachhuang4026.quarkus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleVolumesResponse {
    @SerializedName("@context")
    @Expose
    private Context context;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("itemListElement")
    @Expose
    private List<ItemListElement> itemListElement = null;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ItemListElement> getItemListElement() {
        return itemListElement;
    }

    public void setItemListElement(List<ItemListElement> itemListElement) {
        this.itemListElement = itemListElement;
    }
}
