package me.stremyakvann.recipesapp.services;

public interface FileServiceRecipe {
    boolean saveToFile(String json);

    String readFromFile();

}
