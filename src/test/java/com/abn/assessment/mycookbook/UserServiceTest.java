package com.abn.assessment.mycookbook;

import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.domain.User;
import com.abn.assessment.mycookbook.exception.RecipeNotFoundException;
import com.abn.assessment.mycookbook.exception.UserNotFoundException;
import com.abn.assessment.mycookbook.repository.UserRepository;
import com.abn.assessment.mycookbook.service.UserService;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Maryam Tavakoli on 24/08/2022
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    /**
     * Method to test the creation of an User.
     */
    @Test
    @Order(1)
    void testSave() {

        BDDMockito.given(repository.save(Mockito.any(User.class)))
                .willReturn(getMockUser());
        User response = service.save(new User());

        assertNotNull(response);
    }

    /**
     * Method to test the creation of an User.
     */
    @Test
    @Order(2)
    void testSaveRecipe() throws UserNotFoundException, RecipeNotFoundException {

        BDDMockito.given(repository.findById(100L))
                .willReturn(Optional.of(getMockUser()));
        Set<Recipe> response = service.addRecipe(getMockUser().getId(),getMockRecipe().getId());

        assertNotNull(response);
    }

    /**
     * Method that fill a mock of a User to use as return in the tests.
     * @return <code>User</code> object
     */
    private User getMockUser() {
        return new User(100L, "Test User", "123");
    }

    private Recipe getMockRecipe() {
        return new Recipe(10L, "Test Recipe",4,true, "ttt uuu vvv www",new HashSet<>());
    }

    /**
     * Method to remove all User test data.
     */
    @AfterAll
    private void tearDown() {
        repository.deleteAll();
    }

}
