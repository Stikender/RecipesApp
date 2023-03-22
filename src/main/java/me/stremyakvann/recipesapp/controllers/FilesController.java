package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.services.FileIngredientService;
import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FileServiceRecipe fileServiceRecipe;

    private final FileIngredientService fileIngredientService;

    public FilesController(FileServiceRecipe fileServiceRecipe, FileIngredientService fileIngredientService) {
        this.fileServiceRecipe = fileServiceRecipe;
        this.fileIngredientService = fileIngredientService;
    }

    @GetMapping("/export/recipe")
    public ResponseEntity<InputStreamResource> downloadDataFileRecipe() throws FileNotFoundException {
        File file = fileServiceRecipe.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/export/ingredient")
    public ResponseEntity<InputStreamResource> downloadDataFileIngredient() throws FileNotFoundException {
        File fileIngredient = fileIngredientService.getDataFile();
        if (fileIngredient.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileIngredient));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileIngredient.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
