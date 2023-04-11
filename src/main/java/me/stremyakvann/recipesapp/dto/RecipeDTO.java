package me.stremyakvann.recipesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor

public class RecipeDTO {
    private final int id;
    private final String name;
    private final int cookingTime;
    private final List<Ingredient> ingredient;
    private final List<String> instructionCooking;

    public RecipeDTO() {
        this.id = 0;
        this.name = "";
        this.cookingTime = 0;
        this.ingredient = new ArrayList<>();
        this.instructionCooking = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Рецепт " + "\n" +
                getName() + '\n' +
                "Время приготовления = " + getCookingTime() + " минут" + "\n"+
                "Ингридиенты: " + getIngredient() + "\n"+
                "Инструкция приготовления: " + getInstructionCooking();
    }
    public static RecipeDTO from(int id, Recipe recipe){
        return new RecipeDTO(id, recipe.getName(), recipe.getCookingTime(), recipe.getIngredient(), recipe.getInstructionCooking());   };
}
