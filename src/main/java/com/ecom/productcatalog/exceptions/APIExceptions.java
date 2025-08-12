package com.ecom.productcatalog.exceptions;

public class APIExceptions extends RuntimeException{
    private static final Long serialVersionUID =1L;

    public APIExceptions() {
    }
    public APIExceptions(String message){
        super(message);
    }
}
