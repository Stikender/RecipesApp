package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.dto.IngredientDTO;
import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientsServiceImpl ingredientsServiceImpl;

    public IngredientController(IngredientsServiceImpl ingredientsServiceImpl) {
        this.ingredientsServiceImpl = ingredientsServiceImpl;
    }

    @GetMapping
    public List<IngredientDTO> getAllIngredient() {
        return ingredientsServiceImpl.getAllIngredient();
    }
    @GetMapping("/{id}")
    public IngredientDTO getIngredient(@PathVariable("id") int id){
        return ingredientsServiceImpl.getIngredient(id);
    }
    @PostMapping
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient){
        return ingredientsServiceImpl.addIngredient(ingredient);
    }
    @PutMapping("/{id}")
    public IngredientDTO editIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return ingredientsServiceImpl.editIngredient(id, ingredient);
    }
    @DeleteMapping("/{id}")
    public IngredientDTO deleteIngredient(@PathVariable("id") int id) {
        return ingredientsServiceImpl.deleteIngredient(id);
    }

    @DeleteMapping
    public void deleteAllIngredient() {
        ingredientsServiceImpl.deleteAllIngredient();
    }
}
