package com.lambdasys.shoppingcart.controller;


import com.lambdasys.shoppingcart.dto.CartDto;
import com.lambdasys.shoppingcart.dto.ItemDto;
import com.lambdasys.shoppingcart.exception.CartNotFoundException;
import com.lambdasys.shoppingcart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartController implements Serializable {

    private final CartService service;

    @GetMapping("/{id}")
    public CartDto findById(@PathVariable("id") final String id) throws CartNotFoundException {
        return this.service.findById(id);
    }

    @PostMapping("/{id}")
    public CartDto addItem(@PathVariable("id") final String id, @RequestBody @Valid ItemDto itemDto) {
        var cartDto = this.service.create(id,CartDto.builder().id(id).build());
        cartDto.getItens().add(itemDto);
        return service.save(cartDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final String id) throws CartNotFoundException {
        this.service.deleteById(id);
    }

}
