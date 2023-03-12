package me.stremyakvann.recipesapp.dto;

import me.stremyakvann.recipesapp.model.Ingredient;

public class IngredientDTO {
    private final int id;
    private final String name;
    private final int count;
    private final String measure;

    public IngredientDTO(int id, String name, int count, String measure) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.measure = measure;
    }

    public int getId() {
        return id;
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
    public static IngredientDTO from(int id, Ingredient ingredient){
        return new IngredientDTO(id, ingredient.getName(), ingredient.getCount(), ingredient.getMeasure());
    }
}
