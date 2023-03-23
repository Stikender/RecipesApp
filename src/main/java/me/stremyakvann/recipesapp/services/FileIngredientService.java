package me.stremyakvann.recipesapp.services;

import java.io.File;

public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
