package edu.uchicago.zachhuang4026.quarkus.Models;

import java.io.Serializable;

public class Item implements Serializable {
    private String id;
    private String name;
    private String origin;

    public Item(String id, String name, String origin) {
        this.id = id;
        this.name = name;
        this.origin = origin;
    }

    public Item(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
