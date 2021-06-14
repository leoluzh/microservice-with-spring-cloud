package com.lambdasys.shoppingcart.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto implements Serializable {

    private String id;

    @Builder.Default
    private List<ItemDto> itens = new ArrayList<>();

}
