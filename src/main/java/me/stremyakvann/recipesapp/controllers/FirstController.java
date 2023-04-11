package me.stremyakvann.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String appStart() {
        return "Приложение запущено!";
    }

    @GetMapping("/info")
    public String info() {
        return "Имя ученика: Стремякова Надежда, " +
                "Название проекта: RecipesApp, " +
                "Дата создания проекта: 28.02.2023г., " +
                "Описание проекта: Рецепты любимых блюд";
    }
}
