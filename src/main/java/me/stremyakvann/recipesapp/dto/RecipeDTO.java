package me.stremyakvann.recipesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.model.Recipe;

import java.util.List;
@Data
@AllArgsConstructor

public class RecipeDTO {
    private final int id;
    private final String name;
    private final int cookingTime;
    private final List<Ingredient> ingredient;
    private final List<String> instructionCooking;


    public static RecipeDTO from(int id, Recipe recipe){
        return new RecipeDTO(id, recipe.getName(), recipe.getCookingTime(), recipe.getIngredient(), recipe.getInstructionCooking());   };
}
