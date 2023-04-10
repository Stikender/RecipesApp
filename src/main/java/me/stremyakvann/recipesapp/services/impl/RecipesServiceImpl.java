package me.stremyakvann.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.exception.RecipeNotFoundException;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import me.stremyakvann.recipesapp.services.RecipesService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    private final FileServiceRecipe fileServiceRecipe;
    private final ObjectMapper objectMapper;
    private int idCounter = 0;

    private Map<Integer, Recipe> recipes = new LinkedHashMap<>();

    public RecipesServiceImpl(FileServiceRecipe fileServiceRecipe, ObjectMapper objectMapper) {
        this.fileServiceRecipe = fileServiceRecipe;
        this.objectMapper = objectMapper;
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
        saveToFile();
        return RecipeDTO.from(id,deleteRecipe);
    }

    @Override
    public void deleteAllRecipe() {
        recipes.clear();
        saveToFile();
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
        try {
            String json = fileServiceRecipe.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void recipeTxtFormatter(PrintWriter writer) {
        for (Recipe recipe : this.recipes.values()) {
            writer.println(recipe.getName());
            writer.println("Время приготовления: "+ recipe.getCookingTime() +" минут.");
            writer.println("Ингридиенты:");
            for (Ingredient ingredient : recipe.getIngredient()) {
                writer.println(ingredient.getName() + " - " + ingredient.getCount() + " " +
                        ingredient.getMeasure() + ".");
            }
            writer.println("Инструкция приготовления:");
            for (int i = 0; i < recipe.getInstructionCooking().size(); i++) {
                writer.println(i+1 +". " + recipe.getInstructionCooking().get(i) + ".");
            }
        }
        writer.flush();
    }


}
