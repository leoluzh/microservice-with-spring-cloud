package com.lambdasys.productcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductNotCreatedException extends Exception {

    public ProductNotCreatedException(String message){
        super(message);
    }

}
