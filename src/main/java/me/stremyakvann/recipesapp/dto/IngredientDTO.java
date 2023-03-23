package me.stremyakvann.recipesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.stremyakvann.recipesapp.model.Ingredient;
@Data
@AllArgsConstructor
public class IngredientDTO {
    private final int id;
    private final String name;
    private final int count;
    private final String measure;

    public IngredientDTO() {
        this.id = 0;
        this.name = "";
        this.count = 0;
        this.measure = "";
    }




    public static IngredientDTO from(int id, Ingredient ingredient){
        return new IngredientDTO(id, ingredient.getName(), ingredient.getCount(), ingredient.getMeasure());
    }
}
