package com.lambdasys.productcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(indexName = "product")
public class Product implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer amount;

    @Field(type = FieldType.Keyword)
    private Category category;

    public enum Category {
        ELETRONICS,
        COMPUTERS,
        SMART_HOME,
        ARTS,
        CRAFTS,
        AUTOMOTIVE,
        BEAUTY,
        PERSONAL_CARE,
        WOMENS_FASHION,
        MENS_FASHION,
        GIRLS_FASHION,
        BOYS_FASHION,
        HEALTH,
        HOUSEHOLD,
        HOME,
        KITCHEN,
        INDUSTRIAL,
        SCIENTIFIC,
        LUGGAGE,
        MOVIES,
        TELEVISION,
        PET_SUPPLIES,
        SOFTWARE,
        SPORTS,
        OUTDOORS,
        TOOLS,
        HOME_IMPROVEMENT,
        TOYS,
        GAMES,
        VIDEO_GAMES
    };
}
