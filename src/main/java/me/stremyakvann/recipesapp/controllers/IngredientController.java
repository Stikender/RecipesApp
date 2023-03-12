package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientsServiceImpl ingredientsServiceImpl;

    public IngredientController(IngredientsServiceImpl ingredientsServiceImpl) {
        this.ingredientsServiceImpl = ingredientsServiceImpl;
    }

    @GetMapping("/{id}")
    public IngredientDTO getIngredient(@PathVariable("id") int id){
        return ingredientsServiceImpl.getIngredient(id);
    }
    @PostMapping
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient){
        return ingredientsServiceImpl.addIngredient(ingredient);
    }

}
