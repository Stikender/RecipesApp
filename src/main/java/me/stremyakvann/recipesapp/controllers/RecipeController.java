package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipesServiceImpl recipesServiceImpl;

    public RecipeController(RecipesServiceImpl recipesServiceImpl) {
        this.recipesServiceImpl = recipesServiceImpl;
    }

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipesServiceImpl.getAllRecipes();    }
    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.getRecipe(id);
    }

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipesServiceImpl.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    public RecipeDTO editRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return recipesServiceImpl.editRecipe(id,recipe);
    }

    @DeleteMapping("/{id}")
    public RecipeDTO deleteRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.deleteRecipe(id);
    }

    @DeleteMapping
    public void deleteAllRecipe() {
    recipesServiceImpl.deleteAllRecipe();
    }

}
