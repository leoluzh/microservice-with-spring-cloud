package com.lambdasys.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends Exception {

    public CartNotFoundException(final String cardId){
        super(String.format("Cart with id %s not found.",cardId));
    }

}
