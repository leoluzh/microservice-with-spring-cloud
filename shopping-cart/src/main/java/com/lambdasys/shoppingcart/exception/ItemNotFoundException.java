package com.lambdasys.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(final String productId){
        super(String.format("Item with id %s not found.",productId));
    }

}
