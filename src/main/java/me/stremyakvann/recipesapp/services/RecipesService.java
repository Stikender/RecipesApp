package me.stremyakvann.recipesapp.services;

import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.model.Recipe;

public interface RecipesService {

    public RecipeDTO addRecipe(Recipe recipe);

    public RecipeDTO getRecipe(int id);
}
