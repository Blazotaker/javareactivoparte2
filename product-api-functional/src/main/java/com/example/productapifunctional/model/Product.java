package com.example.productapifunctional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;

}
