package me.stremyakvann.recipesapp.controllers;

import me.stremyakvann.recipesapp.services.FileIngredientService;
import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
        File fileRecipe = fileServiceRecipe.getDataFile();
        if (fileRecipe.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileRecipe));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileRecipe.length())
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
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile fileRecipe) {
        fileServiceRecipe.cleanDataFile();
        File dataFileRecipe = fileServiceRecipe.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFileRecipe)) {
            IOUtils.copy(fileRecipe.getInputStream(), fos);
            return ResponseEntity.ok().build();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile fileIngredient) {
        fileIngredientService.cleanDataFile();
        File dataFileIngredient = fileIngredientService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFileIngredient)) {
            IOUtils.copy(fileIngredient.getInputStream(), fos);
            return ResponseEntity.ok().build();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
