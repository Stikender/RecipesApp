package me.stremyakvann.recipesapp.dto;

import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.model.Recipe;

import java.util.List;

public class RecipeDTO {
    private final int id;
    private final String name;
    private final int cookingTime;
    private final List<Ingredient> ingredient;
    private final List<String> instructionCooking;

    public RecipeDTO(int id, String name, int cookingTime, List<Ingredient> ingredient, List<String> instructionCooking) {
        this.id = id;
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredient = ingredient;
        this.instructionCooking = instructionCooking;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public List<String> getInstructionCooking() {
        return instructionCooking;
    }

    public static RecipeDTO from(int id, Recipe recipe){
        return new RecipeDTO(id, recipe.getName(), recipe.getCookingTime(), recipe.getIngredient(), recipe.getInstructionCooking());   };
}
