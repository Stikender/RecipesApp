package me.stremyakvann.recipesapp.services;

public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();
}
