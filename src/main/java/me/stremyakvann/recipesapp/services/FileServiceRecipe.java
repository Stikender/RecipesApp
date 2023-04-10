package me.stremyakvann.recipesapp.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface FileServiceRecipe {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
