package com.lambdasys.productcatalog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

@Data
@ConfigurationProperties(value="spring.elasticsearch.rest")
public class ElasticsearchProperties {

    private List<String> uris;
    private Duration socketTimeout;
    private Duration connectTimeout;

}
