package me.stremyakvann.recipesapp.services;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;

import java.util.List;

public interface IngredientsService {

    public IngredientDTO addIngredient(Ingredient ingredient);

    List<IngredientDTO> getAllIngredient();

    public IngredientDTO getIngredient(int id);

    IngredientDTO editIngredient(int id, Ingredient ingredient);

    IngredientDTO deleteIngredient(int id);

    void deleteAllIngredient();
}
