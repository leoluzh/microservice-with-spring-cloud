package com.lambdasys.shoppingcart.service;

import com.lambdasys.shoppingcart.dto.CartDto;
import com.lambdasys.shoppingcart.exception.CartNotFoundException;
import com.lambdasys.shoppingcart.mapper.CartMapper;
import com.lambdasys.shoppingcart.model.Cart;
import com.lambdasys.shoppingcart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartService implements Serializable {

    private final CartRepository repository;
    private final CartMapper mapper = CartMapper.INSTANCE;

    public CartDto findById(final String id) throws CartNotFoundException {
        var cart = verifyIfExists(id);
        return mapper.toDto(cart);
    }

    public void deleteById(final String id) throws CartNotFoundException {
        var cart = verifyIfExists(id);
        this.repository.deleteById(id);
    }

    public CartDto create(final String id, final CartDto cartDto){
        try {
            var cart = verifyIfExists(id);
            return mapper.toDto(cart);
        }catch(CartNotFoundException ex){
           var cart = mapper.toModel(cartDto);
           cart.setId(UUID.randomUUID().toString());
           return mapper.toDto(this.repository.save(cart));
        }
    }

    public CartDto update(final String id, final CartDto cartDto) throws CartNotFoundException {
        verifyIfExists(id);
        return save(cartDto);
    }

    public Cart verifyIfExists(final String id) throws  CartNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    public CartDto save(final CartDto cartDto){
        var cart = mapper.toModel(cartDto);
        cart = this.repository.save(cart);
        return mapper.toDto(cart);
    }

}
