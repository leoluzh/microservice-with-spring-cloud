package com.lambdasys.productcatalog.config;

import com.lambdasys.productcatalog.properties.ElasticsearchProperties;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.lambdasys.productcatalog.repository")
@ComponentScan
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ElasticsearchConfig {

    private final ElasticsearchProperties properties;

    @Bean
    public RestHighLevelClient client() {
        var cc = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9300")
                .build();
        return RestClients.create(cc).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

}
