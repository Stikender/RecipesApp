package me.stremyakvann.recipesapp.services;

import java.io.File;
import java.nio.file.Path;

public interface FileServiceRecipe {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

}
