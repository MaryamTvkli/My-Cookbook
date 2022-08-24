package com.abn.assessment.mycookbook.exception;

public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException(){
        super();
    }

    public RecipeNotFoundException(String msg){
        super(msg);
    }

    public RecipeNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}
