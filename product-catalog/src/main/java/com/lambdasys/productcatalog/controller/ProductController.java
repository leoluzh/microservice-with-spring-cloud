package com.lambdasys.productcatalog.controller;

import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.exception.ProductNotFoundException;
import com.lambdasys.productcatalog.model.Product;
import com.lambdasys.productcatalog.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> save(@RequestBody @Valid final ProductDto productDto) throws ProductNotFoundException {
        log.info("Calling create product ...");
        log.info("Product.name = " + productDto.getName() );
        log.info("Product.amout = " + productDto.getAmount() );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(productDto));
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<ProductDto>> save(@RequestBody final List<@Valid ProductDto> productsDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(productsDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> listAll(final Sort sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ProductDto>> listAll(final Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> findById(@PathVariable("id") final String id) throws ProductNotFoundException{
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") final String id) throws ProductNotFoundException {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> update(
            @PathVariable("id") final String id,
            @RequestBody @Valid final ProductDto productDto) throws ProductNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.update(id,productDto));
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> findAllByName(@PathVariable("name") String name){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.findAllByName(name));
    }

    @GetMapping("/name/{name}/annotations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> findAllByNameUsingAnnotation(@PathVariable("name") String name){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.findAllByNameUsingAnnotation(name));
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> findAllByCategory(@PathVariable("category") @Valid @NotNull Product.Category category){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.findAllByCategory(category));
    }

    @GetMapping("/category/{category}/annotations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> findAllByCategoryUsingAnnotation(@PathVariable("category") @Valid @NotNull Product.Category category){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.findAllByCategoryUsingAnnotion(category));
    }

}

