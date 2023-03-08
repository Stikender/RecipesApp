package me.stremyakvann.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private Ingredient ingredient;
    private List<String> instructionCooking;

}
