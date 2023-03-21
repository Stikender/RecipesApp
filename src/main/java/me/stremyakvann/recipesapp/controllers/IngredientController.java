package me.stremyakvann.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "CRUD- ендпоинты для работы с ингридиентами")
public class IngredientController {

    private final IngredientsServiceImpl ingredientsServiceImpl;

    public IngredientController(IngredientsServiceImpl ingredientsServiceImpl) {
        this.ingredientsServiceImpl = ingredientsServiceImpl;
    }

    @GetMapping
    @Operation(
            summary = "Поиск всех ингридиентов",
            description = "Поиск осуществляется без заданных параметров"
    )
    public List<IngredientDTO> getAllIngredient() {
        return ingredientsServiceImpl.getAllIngredient();
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингридиента по id(номеру)",
            description = "Поиск осуществляется по параметру id(номер)"
    )
    public IngredientDTO getIngredient(@PathVariable("id") int id){
        return ingredientsServiceImpl.getIngredient(id);
    }
    @PostMapping
    @Operation(
            summary = "Добавление ингридиента",
            description = "Добавление осуществляется по заданным параметрам"
    )
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient){
        return ingredientsServiceImpl.addIngredient(ingredient);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингридиента",
            description = "Возможно редактирование любого заданного параметра"
    )
    public IngredientDTO editIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return ingredientsServiceImpl.editIngredient(id, ingredient);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингридиента",
            description = "Поиск и удаление осуществляется по id(номеру)"
    )
    public IngredientDTO deleteIngredient(@PathVariable("id") int id) {
        return ingredientsServiceImpl.deleteIngredient(id);
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всех ингридиентов",
            description = "Поиск и удаление осуществляется без параметров"
    )
    public void deleteAllIngredient() {
        ingredientsServiceImpl.deleteAllIngredient();
    }
}
