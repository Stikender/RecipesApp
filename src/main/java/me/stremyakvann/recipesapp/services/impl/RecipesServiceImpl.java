package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.RecipesService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {


    private int idCounter = 0;
    private Map<Integer, Recipe> recipes = new LinkedHashMap<>();


    @Override
    public Recipe addRecipe(Recipe recipe) {
        int id = idCounter++;
        return recipes.put(id,recipe);
    }

    public Recipe getRecipe(int id) {
        Recipe recipe = recipes.get(id);
        if (recipe != null) {
            return recipes.put(id, recipe);
        }
        return null;
    }
}
