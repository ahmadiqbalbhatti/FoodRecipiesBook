package com.example.foodrecipiesbook.FireBase;

public class DataModel {
    public int[] images;
    public String[] title;
    public String[] duration;
    public String ingredients;
    public String[] method;
    public boolean isFavorite;


    public DataModel() {
    }

    public DataModel(int[] images, String[] title) {
        this.images = images;
        this.title = title;
    }

    public DataModel(int[] images, String[] title, String[] duration, String ingredients) {
        this.images = images;
        this.title = title;
        this.duration = duration;
        this.ingredients = ingredients;
    }

    public DataModel(int[] images, String[] title, String[] duration, String ingredients, String[] method) {
        this.images = images;
        this.title = title;
        this.duration = duration;
        this.ingredients = ingredients;
        this.method = method;
    }
}
