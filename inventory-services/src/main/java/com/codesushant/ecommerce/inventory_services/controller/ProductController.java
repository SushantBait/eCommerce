package com.codesushant.ecommerce.inventory_services.controller;

import com.codesushant.ecommerce.inventory_services.dto.ProductDto;
import com.codesushant.ecommerce.inventory_services.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllEnventory(){
        List<ProductDto> inventories = productService.getAllInventory();

        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/id")
    public ResponseEntity<ProductDto> getEnventoryById(@PathVariable Long id){
        ProductDto inventory = productService.getProductById(id);

        return ResponseEntity.ok(inventory);
    }
}
