package me.stremyakvann.recipesapp.services.impl;

import me.stremyakvann.recipesapp.model.Ingredient;
import me.stremyakvann.recipesapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService {

    private Map<Long, Ingredient> ingredients = new LinkedHashMap<>();
}
