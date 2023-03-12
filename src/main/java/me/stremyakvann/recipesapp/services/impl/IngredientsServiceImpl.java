package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService {

    private int idCounter = 0;
    private Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();

    @Override
    public IngredientDTO addIngredient(Ingredient ingredient){
        int id = idCounter++;
        ingredients.put(id, ingredient);
        return IngredientDTO.from(id,ingredient);
    }
    @Override
    public IngredientDTO getIngredient(int id) {
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return IngredientDTO.from(id,ingredient);
        }
        return  null;
    }
}
