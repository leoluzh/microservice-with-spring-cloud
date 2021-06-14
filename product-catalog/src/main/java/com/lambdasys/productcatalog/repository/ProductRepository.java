package com.lambdasys.productcatalog.repository;

import com.lambdasys.productcatalog.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,String> {

    List<Product> findAllByName(String name);

    List<Product> findAllByCategory(Product.Category category);

    @Query("{\"match\":{\"name\":\"?0\"}}")
    List<Product> findAllByNameUsingAnnotation(String name);

    @Query("{\"match\":{\"category\":\"?0\"}}")
    List<Product> findAllByCategoryUsingAnnotation(Product.Category category);

}
