package com.abn.assessment.mycookbook.repository;

import com.abn.assessment.mycookbook.domain.Ingredient;
import com.abn.assessment.mycookbook.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>, JpaSpecificationExecutor<Recipe> {
    List<Recipe> findAllByIngredientsInAndIngredientsNotInAndInstructionLike(List<Ingredient> include,List<Ingredient> exclude, String textSearch);
    List<Recipe> findAllByIngredientsNotInAndInstructionLike(List<Ingredient> exclude, String textSearch);
    List<Recipe> findAllByIngredientsInAndInstructionLike(List<Ingredient> include, String textSearch);
    List<Recipe> findAllByInstructionLike(String textSearch);
}
