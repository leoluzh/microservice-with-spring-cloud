package com.lambdasys.productcatalog.controller;

import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.service.HighLevelClientProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/high-level-client")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HighLevelClientController {

    private HighLevelClientProductService service;

/**
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/aggregate/{term}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String,Long>> aggregateByTerms(@PathVariable String term){
        return ResponseEntity.ok(service.aggregateByTerms(term));
    }

    @GetMapping("/product/create-index")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> createIndex(){
        return service.createProductIndex();
    }
**/
}
