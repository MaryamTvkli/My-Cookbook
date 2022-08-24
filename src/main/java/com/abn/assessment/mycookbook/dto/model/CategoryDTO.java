package com.abn.assessment.mycookbook.dto.model;

import com.abn.assessment.mycookbook.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String description;
    private Set<Recipe> recipes;
}
