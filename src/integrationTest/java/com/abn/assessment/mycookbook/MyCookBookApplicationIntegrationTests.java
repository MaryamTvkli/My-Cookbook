package com.abn.assessment.mycookbook;

import com.abn.assessment.mycookbook.controller.filter.RecipeFilter;
import com.abn.assessment.mycookbook.dto.model.RecipeDTO;
import com.abn.assessment.mycookbook.dto.model.UserDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyCookBookApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    private String token;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    @Order(1)
    void testAddRecipeToFavoriteList() {

        RecipeDTO dto123456 = new RecipeDTO(11L,3,false,"mmm nnn ooo ppp",new HashSet<>());

        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");

        //Create a new HttpEntity
        final HttpEntity<RecipeDTO> entity = new HttpEntity<>(dto123456, headers);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port
                + "/my-cookbook/v1/user/300/add-recipe", HttpMethod.POST, entity, String.class);

        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(2)
    void testRemoveRecipeFromFavoriteList() {

        RecipeDTO dto123456 = new RecipeDTO(12L,3,false,"mmm nnn ooo ppp",new HashSet<>());

        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");

        //Create a new HttpEntity
        final HttpEntity<RecipeDTO> entity = new HttpEntity<>(dto123456, headers);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port
                + "/my-cookbook/v1/user/300/remove-recipe", HttpMethod.DELETE, entity, String.class);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(3)
    void testFindAllRecipe() throws ParseException {

        RecipeFilter recipeFilter=new RecipeFilter();


        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");

        //Create a new HttpEntity
        final HttpEntity<RecipeFilter> entity = new HttpEntity<>(recipeFilter, headers);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port
                + "/my-cookbook/v1/recipe/search", HttpMethod.POST, entity, String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}
