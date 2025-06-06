package com.example.demo2.Item;

public class Items {
    String name;
    String quality;
    Integer id;

    public String getName() {
        return name;
    }
 public Items(){

 }
    public Items(String name, String quality, Integer id) {
        this.name = name;
        this.quality = quality;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
