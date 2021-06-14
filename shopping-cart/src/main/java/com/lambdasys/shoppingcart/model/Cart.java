package com.lambdasys.shoppingcart.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("cart")
public class Cart implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Builder.Default
    private List<Item> itens = new ArrayList<>();

}
