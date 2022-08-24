package com.abn.assessment.mycookbook.controller;

import com.abn.assessment.mycookbook.controller.filter.RecipeFilter;
import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.dto.model.RecipeDTO;
import com.abn.assessment.mycookbook.dto.response.Response;
import com.abn.assessment.mycookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/my-cookbook/v1/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(@Validated @RequestBody RecipeFilter filter, BindingResult result) {

        List<Recipe> searchResult =recipeService.findRecipes(filter);
        List<RecipeDTO> itemsDTO = new ArrayList<>();
        searchResult.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));

        return new ResponseEntity<>(itemsDTO, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RecipeDTO>> allRecipe(){

        List<Recipe> searchResult =recipeService.findAll();
        List<RecipeDTO> itemsDTO = new ArrayList<>();
        searchResult.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));

        return new ResponseEntity<>(itemsDTO, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Response<RecipeDTO>> createRecipe(@Validated @RequestBody RecipeDTO recipeDTO, BindingResult result){

        Response<RecipeDTO> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Recipe savedRecipe =recipeService.save(recipeDTO.convertDTOToEntity());

        response.setData(savedRecipe.convertEntityToDTO());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
