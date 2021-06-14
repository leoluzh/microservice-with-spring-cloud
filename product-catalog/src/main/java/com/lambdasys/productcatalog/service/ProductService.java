package com.lambdasys.productcatalog.service;

import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.exception.ProductNotFoundException;
import com.lambdasys.productcatalog.mapper.ProductMapper;
import com.lambdasys.productcatalog.model.Product;
import com.lambdasys.productcatalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public List<ProductDto> findAll(){
        return StreamSupport.stream(
                repository.findAll().spliterator(),
                false)
                .map(mapper::toDto)
                .toList();
    }

    public List<ProductDto> findAll(Sort sort){
        return StreamSupport.stream(
                this.repository.findAll(sort).spliterator(),
                false)
                .map(mapper::toDto)
                .toList();
    }

    public Page<ProductDto> findAll(Pageable pageable){
        return this.repository.findAll(pageable).map(mapper::toDto);
    }

    public ProductDto findById(final String id) throws ProductNotFoundException {
        var product = verifyIfExists(id);
        return mapper.toDto(product);
    }

    public ProductDto create(final ProductDto productDto){
        productDto.setId(UUID.randomUUID().toString());
        return save(productDto);
    }

    public List<ProductDto> create(final List<ProductDto> productsDto){
        return productsDto
                .stream()
                .map(this::create)
                .toList();
    }

    public ProductDto update(final String id, final ProductDto productDto) throws ProductNotFoundException{
        verifyIfExists(id);
        return save(productDto);
    }

    public void deleteById(final String id) throws ProductNotFoundException {
        verifyIfExists(id);
        this.repository.deleteById(id);
    }

    public Product verifyIfExists(String id) throws ProductNotFoundException {
        return this.repository
                .findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
    }

    private ProductDto save(ProductDto productDto){
        var product = mapper.toModel(productDto);
        product = this.repository.save(product);
        return mapper.toDto(product);
    }

    public List<ProductDto> findAllByName(String name){
        return this.repository
                .findAllByName(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ProductDto> findAllByNameUsingAnnotation(String name){
        return this.repository
                .findAllByNameUsingAnnotation(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ProductDto> findAllByCategory(Product.Category category){
        return this.repository
                .findAllByCategory(category)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ProductDto> findAllByCategoryUsingAnnotion(Product.Category category){
        return this.repository
                .findAllByCategoryUsingAnnotation(category)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
