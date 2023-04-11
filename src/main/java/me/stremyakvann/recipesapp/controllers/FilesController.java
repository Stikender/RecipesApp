package me.stremyakvann.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.stremyakvann.recipesapp.model.Recipe;
import me.stremyakvann.recipesapp.services.FileIngredientService;
import me.stremyakvann.recipesapp.services.FileServiceRecipe;
import me.stremyakvann.recipesapp.services.RecipesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FileServiceRecipe fileServiceRecipe;

    private final FileIngredientService fileIngredientService;

    private final RecipesService recipesService;
    public FilesController(FileServiceRecipe fileServiceRecipe, FileIngredientService fileIngredientService, RecipesService recipesService) {
        this.fileServiceRecipe = fileServiceRecipe;
        this.fileIngredientService = fileIngredientService;
        this.recipesService = recipesService;
    }

    @GetMapping("/export/recipe")
    @Operation(
            summary = "Выгрузка файла рецептов в формате json",
            description = "Осуществляется скачивание файла на носитель пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл рецептов выгружен успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
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

    @GetMapping("/export/recipe/txtFormatter")
    @Operation(
            summary = "Выгрузка файла рецептов в формате txt",
            description = "Осуществляется скачивание файла на носитель пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл рецептов выгружен успешно",
                    content = {@Content(mediaType = "application/txt",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/txt",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/txt",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public void downloadRecipeTxt(HttpServletResponse response) throws IOException {
        ContentDisposition disposition = ContentDisposition.attachment()
                .name("recipes.txt")
                .build();
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
            response.setContentType("text/plain");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            recipesService.recipeTxtFormatter(response.getWriter());
    }

    @GetMapping("/export/ingredient")
    @Operation(
            summary = "Выгрузка файла ингридиентов",
            description = "Осуществляется скачивание файла на носитель пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл ингридиентов выгружен успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
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
    @Operation(
            summary = "Загрузка файла рецептов",
            description = "Осуществляется загрузка файла с носителя пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл рецептов загружен успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Выбран не верный тип файла",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
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
    @Operation(
            summary = "Загрузка файла ингридиентов",
            description = "Осуществляется загрузка файла с носителя пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл ингридиентов загружен успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Выбран не верный тип файла",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неверный URL",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )
    })
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile fileIngredient) {
        fileIngredientService.cleanDataFile();
        File dataFileIngredient = fileIngredientService.getDataFile();
        try (FileOutputStream fosIngredient = new FileOutputStream(dataFileIngredient)) {
            IOUtils.copy(fileIngredient.getInputStream(), fosIngredient);
            return ResponseEntity.ok().build();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
