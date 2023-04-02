package me.stremyakvann.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все рецепты найдены",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public List<RecipeDTO> getAllRecipes() {
        return recipesServiceImpl.getAllRecipes();    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта по id(номеру)",
            description = "Поиск осуществляется по параметру id(номер)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найден рецепт по id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введен неверный id, рецепт не найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.getRecipe(id);
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта",
            description = "Добавление осуществляется по заданным параметрам"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipesServiceImpl.addRecipe(recipe);
    }

    @PostMapping("/txt")
    public RecipeDTO addRecipeTxt(@RequestBody Recipe recipe) {
        return recipesServiceImpl.addRecipeTxt(recipe);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта",
            description = "Возможно редактирование любого заданного параметра"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Редактирование рецепта по id выполнено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введен неверный id, рецепт не отредактирован",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public RecipeDTO editRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return recipesServiceImpl.editRecipe(id,recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Поиск и удаление осуществляется по id(номеру)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление рецепта по id выполнено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введен неверный id, рецепт не удален",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public RecipeDTO deleteRecipe(@PathVariable("id") int id) {
        return recipesServiceImpl.deleteRecipe(id);
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всех рецептов",
            description = "Поиск и удаление осуществляется без параметров"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление всех рецептов выполнено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public void deleteAllRecipe() {
    recipesServiceImpl.deleteAllRecipe();
    }


}
