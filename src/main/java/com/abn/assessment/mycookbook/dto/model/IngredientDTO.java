package com.abn.assessment.mycookbook.dto.model;

import com.abn.assessment.mycookbook.domain.Ingredient;
import com.abn.assessment.mycookbook.domain.Recipe;
import com.abn.assessment.mycookbook.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Getter
@Setter
public class IngredientDTO {
    private Long id;
    private String name;
    private BigDecimal amount;
    private UnitOfMeasure uom;
    private Recipe recipe;
    public Ingredient convertDTOToEntity(){
        return new ModelMapper().map(this,Ingredient.class);

    }
}
