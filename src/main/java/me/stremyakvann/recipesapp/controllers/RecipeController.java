package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipesServiceImpl recipesService;

    public RecipeController(RecipesServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") int id) {
        return recipesService.getRecipe(id);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipesService.addRecipe(recipe);
    }
}
