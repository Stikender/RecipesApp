package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.exception.IngredientNotFoundException;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    public List<IngredientDTO> getAllIngredient() {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Map.Entry<Integer, Ingredient> entry : ingredients.entrySet()) {
            ingredientDTOList.add(IngredientDTO.from(entry.getKey(), entry.getValue()));
        }
        return ingredientDTOList;
    }
    @Override
    public IngredientDTO getIngredient(int id) {
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return IngredientDTO.from(id,ingredient);
        }
        return  null;
    }

    @Override
    public IngredientDTO editIngredient(int id, Ingredient ingredient) {
        Ingredient editIngredient = ingredients.get(id);
        if (editIngredient == null) {
            throw new IngredientNotFoundException();
        }
        ingredients.put(id, ingredient);
        return IngredientDTO.from(id, editIngredient);
    }
    @Override
    public IngredientDTO deleteIngredient(int id) {
        Ingredient deleteIngredient = ingredients.remove(id);
        if (deleteIngredient == null) {
            throw new IngredientNotFoundException();
        }
        return IngredientDTO.from(id, deleteIngredient);
    }

    @Override
    public void deleteAllIngredient() {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Map.Entry<Integer, Ingredient> entry : ingredients.entrySet()) {
            ingredientDTOList.remove(IngredientDTO.from(entry.getKey(), entry.getValue()));
        }
    }
}
