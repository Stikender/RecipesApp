package me.stremyakvann.recipesapp.services;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;

public interface IngredientsService {

    public IngredientDTO addIngredient(Ingredient ingredient);

    public IngredientDTO getIngredient(int id);
}
