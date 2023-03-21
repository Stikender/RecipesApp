package me.stremyakvann.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.stremyakvann.recipesapp.dto.RecipeDTO;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD- ендпоинты для работы с рецептами")
public class RecipeController {

    private final RecipesServiceImpl recipesServiceImpl;

    public RecipeController(RecipesServiceImpl recipesServiceImpl) {
        this.recipesServiceImpl = recipesServiceImpl;
    }

    @GetMapping
    @Operation(
            summary = "Поиск всех рецептов",
            description = "Поиск осуществляется без заданных параметров"
    )
    public List<RecipeDTO> getAllRecipes() {
        return recipesServiceImpl.getAllRecipes();    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта по id(номеру)",
            description = "Поиск осуществляется по параметру id(номер)"
    )
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.getRecipe(id);
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта",
            description = "Добавление осуществляется по заданным параметрам"
    )
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipesServiceImpl.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта",
            description = "Возможно редактирование любого заданного параметра"
    )
    public RecipeDTO editRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return recipesServiceImpl.editRecipe(id,recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Поиск и удаление осуществляется по id(номеру)"
    )
    public RecipeDTO deleteRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.deleteRecipe(id);
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всех рецептов",
            description = "Поиск и удаление осуществляется без параметров"
    )
    public void deleteAllRecipe() {
    recipesServiceImpl.deleteAllRecipe();
    }

}
