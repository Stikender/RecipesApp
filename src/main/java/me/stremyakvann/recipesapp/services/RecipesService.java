package me.stremyakvann.recipesapp.services;

import me.stremyakvann.recipesapp.model.Recipe;

public interface RecipesService {

    public Recipe addRecipe(Recipe recipe);

    public Recipe getRecipe(int id);
}
