package com.example.foodrecipiesbook.FireBase;

public class DataModel {
    public int image;
    public String title;
    public String duration;
    public String ingredients;
    public String method;
    public boolean isFavorite;


    public DataModel() {
    }

    public DataModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public DataModel(int image, String title, String duration, String ingredients) {
        this.image = image;
        this.title = title;
        this.duration = duration;
        this.ingredients = ingredients;
    }

    public DataModel(int image, String title, String duration, String ingredients, String method, boolean isFavorite) {
        this.image = image;
        this.title = title;
        this.duration = duration;
        this.ingredients = ingredients;
        this.method = method;
        this.isFavorite = isFavorite;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
