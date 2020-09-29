package com.example.productapifunctional.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductEvent {
    private Long eventId;
    private String evenType;
}
