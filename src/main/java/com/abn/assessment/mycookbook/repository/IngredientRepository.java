package com.abn.assessment.mycookbook.repository;

import com.abn.assessment.mycookbook.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
