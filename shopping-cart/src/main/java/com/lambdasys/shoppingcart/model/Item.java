package com.lambdasys.shoppingcart.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("item")
public class Item implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String productId;

    private Integer amount;

}
