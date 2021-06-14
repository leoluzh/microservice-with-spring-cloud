package com.lambdasys.productcatalog.service;

import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.exception.ProductNotFoundException;
import com.lambdasys.productcatalog.mapper.ProductMapper;
import com.lambdasys.productcatalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@AllArgsConstructor))
public class ElasticsearchRestTemplateService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public ProductDto findById(String id) throws ProductNotFoundException {

        SearchHits<Product> sh =
                elasticsearchRestTemplate
                        .search(new CriteriaQuery(
                                        new Criteria("id").is(id)),
                                Product.class
                        );

        return sh.get()
                .map(SearchHit::getContent)
                .map(mapper::toDto)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    public List<ProductDto> findAllByName(String name) {

        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name))
                .build();

        SearchHits<Product> sh = elasticsearchRestTemplate.search(query, Product.class);

        return sh.get()
                .map(SearchHit::getContent)
                .map(mapper::toDto)
                .toList();
    }

    public Map<String, Long> aggregateTerm(String term) {

        Query query = new NativeSearchQueryBuilder()
                .addAggregation(new TermsAggregationBuilder(term).field(term).size(10))
                .build();

        SearchHits<Product> sh = elasticsearchRestTemplate.search(query, Product.class);

        Map<String, Long> result = new HashMap<>();

        sh.getAggregations()
                .asList().forEach(aggregation -> {
            ((Terms) aggregation).getBuckets().forEach(bucket -> {
                result.put(bucket.getKeyAsString(), bucket.getDocCount());
            });
        });

        return result;
    }

}
