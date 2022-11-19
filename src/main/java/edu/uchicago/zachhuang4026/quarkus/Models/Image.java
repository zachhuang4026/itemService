package edu.uchicago.zachhuang4026.quarkus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Image {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
