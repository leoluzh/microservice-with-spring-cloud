package com.lambdasys.productcatalog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdasys.productcatalog.dto.ProductDto;
import com.lambdasys.productcatalog.exception.ProductNotCreatedException;
import com.lambdasys.productcatalog.exception.ProductNotFoundException;
import com.lambdasys.productcatalog.mapper.ProductMapper;
import com.lambdasys.productcatalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class HighLevelClientProductService {

    private final RestHighLevelClient restHighLevelClient;
    private final ProductMapper mapper = ProductMapper.INSTANCE;
    private final ObjectMapper objectMapper;

/**
    public ProductDto create(ProductDto productDto) throws ProductNotCreatedException  {
        final var request = new IndexRequest("product");
        final var id = UUID.randomUUID().toString();
        final var product = mapper.toModel(productDto);
        request.id(id);
        request.source(product);
        try{
            var response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            if( response.status() == RestStatus.ACCEPTED){
                return mapper.toDto(product);
            }
            throw new ProductNotCreatedException(String.format("Cannot create product. Wrong status %s",response.status()));
        }catch(Exception ex){
            log.error("Error indexing product: {}",product,ex);
            throw new ProductNotCreatedException("Cannot create product.");
        }
    }

    public List<ProductDto> insert(List<ProductDto> productsDto) throws ProductNotCreatedException{

        final var bulkRequest = new BulkRequest();

        productsDto.stream().forEach( productDto -> {
           final var indexRequest = new IndexRequest("product");
           productDto.setId(UUID.randomUUID().toString());
           indexRequest.index(productDto.getId());
           indexRequest.source(mapper.toModel(productDto));
           bulkRequest.add(indexRequest);
        });

        try{
            final var bulkResponse = restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
            if( !bulkResponse.hasFailures() ){
                return productsDto;
            }else{
                throw new ProductNotCreatedException(String.format("Cannot create product. Wrong status %s",bulkResponse.status()));
            }
        }catch (IOException ex){
            log.error("Error indexing bulking product: {}",ex.getMessage());
            throw new ProductNotCreatedException("Error indexing bulking product.");
        }
    }

    public ProductDto findById(String id) throws ProductNotFoundException{

        final var searchRequest = new SearchRequest("product");
        final var searchSourceBuilder = SearchSourceBuilder.searchSource();
        final var idsQueryBuilder = QueryBuilders.idsQuery().addIds(id);

        searchSourceBuilder.query(idsQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        try{

            final var searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);

            return toProductList(searchResponse.getHits().getHits())
                    .stream()
                    .map(mapper::toDto)
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException(id));

        }catch(Exception ex){
            log.error("Error finding product: {}",ex.getMessage());
            throw new ProductNotFoundException(id);
        }

    }

    public boolean delete(String id) {

        final var deleteRequest = new DeleteRequest();
        deleteRequest.id(id);

        try{
            final var deleteResponse = restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
            if( deleteResponse.status() == RestStatus.ACCEPTED ){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            log.error("Error deleting product: {}",ex.getMessage());
            return false;
        }
    }

    public Map<String,Long> aggregateTerm(String term){

      final var searchRequest = new SearchRequest("product");
      final var searchSourceBuilder = SearchSourceBuilder.searchSource();
      final var terms = AggregationBuilders.terms(term).field(term);

      searchSourceBuilder.size(0);
      searchSourceBuilder.aggregation(terms);
      searchRequest.source(searchSourceBuilder);

      try{

      }catch(IOException ex){
          log.error(ex.getMessage());
      }

    }

    private List<Product> toProductList(SearchHit[] searchHits) throws Exception {
        final var products = new ArrayList<Product>();
        for(var searchHit : searchHits){
            products.add(objectMapper.readValue(searchHit.getSourceAsString(),Product.class));
        }
        return products;
    }
**/
}
