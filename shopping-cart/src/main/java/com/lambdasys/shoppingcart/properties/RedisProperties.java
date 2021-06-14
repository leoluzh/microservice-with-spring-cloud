package com.lambdasys.shoppingcart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("redis")
public class RedisProperties {

    private String url;
    private Long port;

}
