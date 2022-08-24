package com.abn.assessment.mycookbook.service;

import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.domain.User;
import com.abn.assessment.mycookbook.exception.RecipeNotFoundException;
import com.abn.assessment.mycookbook.exception.UserNotFoundException;
import com.abn.assessment.mycookbook.repository.RecipeRepository;
import com.abn.assessment.mycookbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public Set<Recipe> addRecipe(Long userId,Long recipeId) throws UserNotFoundException, RecipeNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id=" + userId + " not found"));
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(() ->
                new RecipeNotFoundException("Recipe id=" + recipeId + " not found"));
        Set<Recipe> recipeList=user.getRecipes();
        recipeList.add(recipe);
        user.setRecipes(recipeList);
        save(user);
        return user.getRecipes();
    }

    public Set<Recipe> removeRecipe(Long userId,Long recipeId) throws UserNotFoundException, RecipeNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id=" + userId + " not found"));
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(() ->
                new RecipeNotFoundException("Recipe id=" + userId + " not found"));
        Set<Recipe> recipeList=user.getRecipes();
        recipeList.remove(recipe);
        user.setRecipes(recipeList);
        save(user);
        return user.getRecipes();
    }

    public Set<Recipe> getFavoriteRecipe(Long userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id=" + userId + " not found"));
        return user.getRecipes();
    }
}
