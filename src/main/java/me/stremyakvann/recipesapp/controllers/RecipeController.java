package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipesServiceImpl recipesServiceImpl;

    public RecipeController(RecipesServiceImpl recipesServiceImpl) {
        this.recipesServiceImpl = recipesServiceImpl;
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.getRecipe(id);
    }

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipesServiceImpl.addRecipe(recipe);
    }
}
