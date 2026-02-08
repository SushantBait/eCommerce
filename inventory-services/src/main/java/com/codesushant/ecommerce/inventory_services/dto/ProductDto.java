package com.codesushant.ecommerce.inventory_services.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private String title;

    private Double price;

    private Integer stock;
}
