package me.stremyakvann.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.exception.RecipeNotFoundException;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import me.stremyakvann.recipesapp.services.RecipesService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    private final FileServiceRecipe fileServiceRecipe;

    private int idCounter = 0;

    private Map<Integer, Recipe> recipes = new LinkedHashMap<>();

    public RecipesServiceImpl(FileServiceRecipe fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }
    @PostConstruct
    private void init() {
    readFromFile();
    }
    @Override
    public RecipeDTO addRecipe(Recipe recipe) {
        int id = idCounter++;
        recipes.put(id, recipe);
        saveToFile();
        return RecipeDTO.from(id, recipe);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
    List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for (Map.Entry<Integer, Recipe> entry : recipes.entrySet()) {
            recipeDTOList.add(RecipeDTO.from(entry.getKey(), entry.getValue()));
        }
    return recipeDTOList;
    }
    @Override
    public RecipeDTO getRecipe(int id) {
        Recipe recipe = recipes.get(id);
        if (recipe != null) {
            return RecipeDTO.from(id, recipe);
        }
        return null;
    }

    @Override
    public RecipeDTO editRecipe(int id, Recipe recipe) {
        Recipe editRecipe = recipes.get(id);
        if (editRecipe == null) {
            throw new RecipeNotFoundException();
        }
        recipes.put(id, recipe);
        saveToFile();
        return RecipeDTO.from(id, editRecipe);
    }
    @Override
    public RecipeDTO deleteRecipe(int id) {
        Recipe deleteRecipe = recipes.remove(id);
        if (deleteRecipe == null) {
            throw new RecipeNotFoundException();
        }
        return RecipeDTO.from(id,deleteRecipe);
    }

    @Override
    public void deleteAllRecipe() {
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for (Map.Entry<Integer, Recipe> entry : recipes.entrySet()) {
            recipeDTOList.remove(RecipeDTO.from(entry.getKey(), entry.getValue()));
        }
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileServiceRecipe.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
    String json = fileServiceRecipe.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
