package me.stremyakvann.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List <Ingredient> ingredient;
    private List<String> instructionCooking;

    @Override
    public String toString() {
        return "Рецепт " + "\n" +
                getName() + '\n' +
                "Время приготовления = " + cookingTime + " минут" + "\n"+
                "Ингридиенты: " + ingredient + "\n"+
                "Инструкция приготовления: " + instructionCooking;
    }
}
