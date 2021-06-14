package com.lambdasys.productcatalog.mapper;

import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDto productDto);
    ProductDto toDto(Product product);

}
