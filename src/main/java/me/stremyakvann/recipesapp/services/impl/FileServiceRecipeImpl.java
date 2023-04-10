package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileServiceRecipe {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.recipe.file}")
    private String dataFileNameRecipe;

//    @Override
//    public ResponseEntity<InputStreamResource> downloadDataFileRecipe() throws FileNotFoundException {
//        File fileRecipe = getDataFile();
//        if (fileRecipe.exists()) {
//            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileRecipe));
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .contentLength(fileRecipe.length())
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
//                    .body(resource);
//        } else {
//            return ResponseEntity.noContent().build();
//        }
//    }
//    @Override
//    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile fileRecipe) {
//        cleanDataFile();
//        File dataFileRecipe = getDataFile();
//        try (FileOutputStream fos = new FileOutputStream(dataFileRecipe)) {
//            IOUtils.copy(fileRecipe.getInputStream(), fos);
//            return ResponseEntity.ok().build();
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
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
