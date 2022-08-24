package com.abn.assessment.mycookbook;

import com.abn.assessment.mycookbook.controller.filter.RecipeFilter;
import com.abn.assessment.mycookbook.domain.Ingredient;
import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.domain.UnitOfMeasure;
import com.abn.assessment.mycookbook.repository.IngredientRepository;
import com.abn.assessment.mycookbook.repository.RecipeRepository;
import com.abn.assessment.mycookbook.service.RecipeService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Maryam Tavakoli on 24/08/2022
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
class RecipeServiceTest {
    @Autowired
    private RecipeService service;

    @MockBean
    private RecipeRepository repository;

    @MockBean
    private IngredientRepository ingredientRepository;

    /**
     * Method to test the creation of an User.
     */
    @Test
    @Order(1)
    void test_search_all_vegetarian() {
        BDDMockito.given(repository.findAllByInstructionLike("%%"))
                .willReturn(getMockRecipeList());
        RecipeFilter recipeFilter=new RecipeFilter();
        recipeFilter.setVegetarian(true);
        List<Recipe> response = service.findRecipes(recipeFilter);

        assertEquals(1, response.size());
    }

    @Test
    @Order(2)
    void test_search_Recipes_serve_4_and_have_potatoes_in_ingredient() {
        BDDMockito.given(repository.findAllByIngredientsInAndInstructionLike(Mockito.any(),Mockito.anyString()))
                .willReturn(getMockRecipeList());
        BDDMockito.given(ingredientRepository.findById(22L))
                .willReturn(Optional.of(getMockIngredient()));
        RecipeFilter recipeFilter=new RecipeFilter();
        recipeFilter.setIncludes("22");
        List<Recipe> response = service.findRecipes(recipeFilter);

        assertEquals(1, response.size());
    }

    @Test
    @Order(3)
    void test_search_Recipes_not_have_salmon_and_oven_in_instructions() {
        BDDMockito.given(repository.findAllByIngredientsNotInAndInstructionLike(Mockito.any(),Mockito.anyString()))
                .willReturn(getMockRecipeList());
        BDDMockito.given(ingredientRepository.findById(22L))
                .willReturn(Optional.of(getMockIngredient()));
        RecipeFilter recipeFilter=new RecipeFilter();
        recipeFilter.setExcludes("22");
        List<Recipe> response = service.findRecipes(recipeFilter);

        assertEquals(1, response.size());
    }

    private List<Recipe> getMockRecipeList() {
        List<Recipe> mockList=new ArrayList<>();
        Set<Ingredient> ingredientSet1=new HashSet<>();
        Ingredient ingredient1=new Ingredient("potatoes", BigDecimal.valueOf(2.0),new UnitOfMeasure(1L,"stock"),new Recipe(10L,"Recipe1",3,true,"oven",ingredientSet1));
        ingredient1.setId(22L);
        Ingredient ingredient2=new Ingredient("oven", BigDecimal.valueOf(2.0),new UnitOfMeasure(1L,"stock"),new Recipe(10L,"Recipe1",3,true,"oven",ingredientSet1));
        ingredient2.setId(23L);
        ingredientSet1.add(ingredient1);
        ingredientSet1.add(ingredient2);
        mockList.add(new Recipe(10L,"Recipe1",4,true,"instruction1",ingredientSet1));
        return mockList;
    }
    private Ingredient getMockIngredient() {
        Set<Ingredient> ingredientSet1=new HashSet<>();
        return new Ingredient("potatoes", BigDecimal.valueOf(2.0),new UnitOfMeasure(1L,"stock"),new Recipe(10L,"Recipe1",3,true,"instruction1",ingredientSet1));
    }
    private List<Ingredient> getMockIngredientList() {
        List<Ingredient> ingredientList=new ArrayList<>();
        Set<Ingredient> ingredientSet1=new HashSet<>();
        Ingredient ingredient=new Ingredient("potatoes", BigDecimal.valueOf(2.0),new UnitOfMeasure(1L,"stock"),new Recipe(10L,"Recipe1",3,true,"instruction1",ingredientSet1));
        ingredientList.add(ingredient);
        return ingredientList;
    }
}
