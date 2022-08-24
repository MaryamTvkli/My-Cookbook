package com.abn.assessment.mycookbook.exception;

public class IngredientNotFoundException extends Exception {


    public IngredientNotFoundException(){
        super();
    }

    public IngredientNotFoundException(String msg){
        super(msg);
    }

    public IngredientNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}