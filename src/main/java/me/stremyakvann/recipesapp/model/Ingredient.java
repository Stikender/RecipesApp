package me.stremyakvann.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Ingredient {
    private String name;
    private int count;
    private String measure;

    public Ingredient(String name, int count, String measure) {
        this.name = name;
        this.count = count;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getMeasure() {
        return measure;
    }
}
