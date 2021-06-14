package com.lambdasys.shoppingcart.mapper;

import com.lambdasys.shoppingcart.dto.CartDto;
import com.lambdasys.shoppingcart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toModel(CartDto cartDto);
    CartDto toDto(Cart cart);

}
