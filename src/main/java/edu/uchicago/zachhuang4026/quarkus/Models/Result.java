package edu.uchicago.zachhuang4026.quarkus.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("@id")
    @Expose
    private String id;
    @SerializedName("@type")
    @Expose
    private List<String> type = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("detailedDescription")
    @Expose
    private DetailedDescription detailedDescription;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DetailedDescription getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(DetailedDescription detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
}
