package me.stremyakvann.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.stremyakvann.recipesapp.model.Recipe;
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
        return fileServiceRecipe.downloadDataFileRecipe();
        }
    @GetMapping("/export/recipe/txt")
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
    public ResponseEntity<InputStreamResource> downloadRecipeTxt() throws FileNotFoundException {
        return fileServiceRecipe.downloadRecipeTxt();
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
        return fileIngredientService.downloadDataFileIngredient();
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
        return fileServiceRecipe.uploadDataFileRecipe(fileRecipe);
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
        return fileIngredientService.uploadDataFileIngredient(fileIngredient);
    }
}
