package com.lambdasys.shoppingcart.mapper;

import com.lambdasys.shoppingcart.dto.ItemDto;
import com.lambdasys.shoppingcart.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item toModel(ItemDto itemDto);
    ItemDto toDto(Item item);

}
