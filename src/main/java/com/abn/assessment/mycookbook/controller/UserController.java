package com.abn.assessment.mycookbook.controller;

import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.domain.User;
import com.abn.assessment.mycookbook.dto.model.RecipeDTO;
import com.abn.assessment.mycookbook.dto.model.UserDTO;
import com.abn.assessment.mycookbook.dto.response.Response;
import com.abn.assessment.mycookbook.exception.RecipeNotFoundException;
import com.abn.assessment.mycookbook.exception.UserNotFoundException;
import com.abn.assessment.mycookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maryam Tavakoli on 24/08/2022
 */

@RestController
@RequestMapping("/my-cookbook/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method that creates a user in the API.
     *
     * @param userDTO
     * @param result
     * @return ResponseEntity with a Response<UserDTO> object and the HTTP status
     *
     * HTTP Status:
     *
     * 201 - Created: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     * 401 - Unauthorized: No valid API key provided.
     * 403 - Forbidden: The API key doesn't have permissions to perform the request.
     * 404 - Not Found: The requested resource doesn't exist.
     * 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
     * 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential back-off of your requests.
     * 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).
     *
     */

    @PostMapping("/new")
    public ResponseEntity<Response<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result)  {
        Response<UserDTO> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User savedUser = userService.save(userDTO.convertDTOToEntity());

        response.setData(savedUser.convertEntityToDTO());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Method that add a recipe a user favorite list in the API.
     *
     * @param userId
     * @param recipeDTO
     * @param result
     * @return ResponseEntity with a Response<Set<RecipeDTO>> object and the HTTP status
     *
     * HTTP Status:
     *
     * 201 - Created: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     * 401 - Unauthorized: No valid API key provided.
     * 403 - Forbidden: The API key doesn't have permissions to perform the request.
     * 404 - Not Found: The requested resource doesn't exist.
     * 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
     * 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential back-off of your requests.
     * 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).
     *
     */

    @PostMapping("/{id}/add-recipe")
    public ResponseEntity<Response<Set<RecipeDTO>>> addRecipeToFavoriteList(@PathVariable("id") Long userId ,@Valid @RequestBody RecipeDTO recipeDTO, BindingResult result) throws UserNotFoundException, RecipeNotFoundException {

        Response<Set<RecipeDTO>> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Set<Recipe> updatedRecipeList = userService.addRecipe(userId,recipeDTO.getId());
        Set<RecipeDTO> updatedList=new HashSet<>();
        updatedRecipeList.forEach(r->updatedList.add(r.convertEntityToDTO()));

        response.setData(updatedList);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    /**
     * Method that delete a unique recipe from user favorite list.
     *
     * @param userId
     * @param recipeDTO
     * @param result
     *
     * @return ResponseEntity with a Response<Set<RecipeDTO>> object and the HTTP status
     *
     * HTTP Status:
     *
     * 204 - OK: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     * 404 - Not Found: The requested resource doesn't exist.
     * 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
     * 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential back-off of your requests.
     * 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).
     *
     * @throws UserNotFoundException
     * @throws RecipeNotFoundException
     */
    @DeleteMapping("/{id}/remove-recipe")
    public ResponseEntity<Response<Set<RecipeDTO>>> removeRecipeToFavoriteList(@PathVariable("id") Long userId , @Valid @RequestBody RecipeDTO recipeDTO, BindingResult result) throws UserNotFoundException, RecipeNotFoundException {

        Response<Set<RecipeDTO>> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Set<Recipe> recipeSet=userService.removeRecipe(userId,recipeDTO.getId());
        Set<RecipeDTO> updatedList=new HashSet<>();
        recipeSet.forEach(r->updatedList.add(r.convertEntityToDTO()));

        response.setData(updatedList);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
