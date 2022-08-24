package com.abn.assessment.mycookbook.controller.filter;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeFilter {
    private String includes="";
    private String excludes="";
    private boolean vegetarian;
    private int servings;
    private String textSearch="";
}
