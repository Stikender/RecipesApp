package me.stremyakvann.recipesapp.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileIngredientService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
