package me.stremyakvann.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;



public class Recipe {

    private String name;
    private int cookingTime;
    private List <Ingredient> ingredient;
    private List<String> instructionCooking;

    public Recipe(String name, int cookingTime, List<Ingredient> ingredient, List<String> instructionCooking) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredient = ingredient;
        this.instructionCooking = instructionCooking;
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
}
