package me.stremyakvann.recipesapp.services;

import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.model.Recipe;

import java.util.List;

public interface RecipesService {

    public RecipeDTO addRecipe(Recipe recipe);

    RecipeDTO addRecipeTxt(Recipe recipe);

    List<RecipeDTO> getAllRecipes();

    public RecipeDTO getRecipe(int id);

    RecipeDTO editRecipe(int id, Recipe recipe);

    RecipeDTO deleteRecipe(int id);

    void deleteAllRecipe();
}
