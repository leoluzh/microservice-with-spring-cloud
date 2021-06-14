package com.lambdasys.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto implements Serializable {

    @NotEmpty
    private String id;

    @NotNull
    @Min(1) @Max(Integer.MAX_VALUE)
    private Integer amount;

}
