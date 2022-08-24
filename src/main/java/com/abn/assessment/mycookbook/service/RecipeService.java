package com.abn.assessment.mycookbook.service;

import com.abn.assessment.mycookbook.controller.filter.RecipeFilter;
import com.abn.assessment.mycookbook.domain.Ingredient;
import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.exception.IngredientNotFoundException;
import com.abn.assessment.mycookbook.repository.IngredientRepository;
import com.abn.assessment.mycookbook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;


    /**
     * @param filter
     * @return
     */
    public List<Recipe> findRecipes(RecipeFilter filter) {
        List<Ingredient> include = new ArrayList<>();
        List<Ingredient> exclude = new ArrayList<>();
        String textSearch = "%".concat(filter.getTextSearch()).concat("%");
        List<Recipe> result;
        if (!filter.getIncludes().equals("")) {
            Arrays.stream(filter.getIncludes().split(",")).toList().forEach(id -> {
                try {
                    include.add(ingredientRepository.findById(Long.parseLong(id)).orElseThrow(() ->
                    new IngredientNotFoundException("Ingredient id=" + id + " not found")));
                } catch (IngredientNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            if (!filter.getExcludes().equals("")) {
                Arrays.stream(filter.getExcludes().split(",")).toList().forEach(id -> {
                    try {
                        exclude.add(ingredientRepository.findById(Long.parseLong(id)).orElseThrow(() ->
                                new IngredientNotFoundException("Ingredient id=" + id + " not found")));
                    } catch (IngredientNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                result = recipeRepository.findAllByIngredientsInAndIngredientsNotInAndInstructionLike(include, exclude, textSearch).stream().distinct().toList();
            } else {
                result = recipeRepository.findAllByIngredientsInAndInstructionLike(include, textSearch).stream().distinct().toList();
            }
        } else {
            if (!filter.getExcludes().equals("")) {
                result = recipeRepository.findAllByIngredientsNotInAndInstructionLike(exclude,textSearch);
            } else {
                result = recipeRepository.findAllByInstructionLike(textSearch).stream().distinct().toList();
            }
        }
        if (filter.isVegetarian())
            result = result.stream().filter(Recipe::isVegan).toList();
        if (filter.getServings() > 0)
            result = result.stream().filter(r -> r.getServings() == filter.getServings()).toList();
        return result;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }
}
