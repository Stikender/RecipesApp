package me.stremyakvann.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.exception.IngredientNotFoundException;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.FileIngredientService;
import me.stremyakvann.recipesapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService {
    private final FileIngredientService fileIngredientService;

    private int idCounter = 0;
    private Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();

    public IngredientsServiceImpl(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }
    @Override
    public IngredientDTO addIngredient(Ingredient ingredient){
        int id = idCounter++;
        ingredients.put(id, ingredient);
        saveToFile();
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
        saveToFile();
        return IngredientDTO.from(id, editIngredient);
    }
    @Override
    public IngredientDTO deleteIngredient(int id) {
        Ingredient deleteIngredient = ingredients.remove(id);
        if (deleteIngredient == null) {
            throw new IngredientNotFoundException();
        }
        saveToFile();
        return IngredientDTO.from(id, deleteIngredient);
    }

    @Override
    public void deleteAllIngredient() {
        ingredients.clear();
        saveToFile();
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileIngredientService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
