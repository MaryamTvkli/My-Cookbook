package com.abn.assessment.mycookbook.dto.model;

import com.abn.assessment.mycookbook.domain.Ingredient;
import com.abn.assessment.mycookbook.domain.Recipe;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RecipeDTO extends RepresentationModel<RecipeDTO> {

    private Long id;

    private Integer servings;
    private boolean vegan;
    private String instruction;
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe convertDTOToEntity(){
        return new ModelMapper().map(this,Recipe.class);
    }
}
