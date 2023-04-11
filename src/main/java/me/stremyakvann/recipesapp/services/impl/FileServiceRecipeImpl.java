package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileServiceRecipe {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.recipe.file}")
    private String dataFileNameRecipe;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileNameRecipe), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileNameRecipe));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileNameRecipe);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileNameRecipe);
    }

}
