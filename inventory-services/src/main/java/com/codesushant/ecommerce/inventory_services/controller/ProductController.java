package com.codesushant.ecommerce.inventory_services.controller;

import com.codesushant.ecommerce.inventory_services.clients.OrdersFeignClient;
import com.codesushant.ecommerce.inventory_services.dto.OrderRequestDto;
import com.codesushant.ecommerce.inventory_services.dto.ProductDto;
import com.codesushant.ecommerce.inventory_services.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final DiscoveryClient discoveryClient;

    private final RestClient restClient;

    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public String fetchFromOrderService(){
        ServiceInstance orderService = discoveryClient.getInstances("order-service").get(0);

//        return restClient.get()
//                .uri(orderService.getUri()+"/orders/core/helloOrder")
//                .retrieve().body(String.class);
//        http://localhost:8080/api/v1/inventory/products/fetchOrders    for calling
        return ordersFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllEnventory(){
        List<ProductDto> inventories = productService.getAllInventory();

        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getEnventoryById(@PathVariable Long id){
        ProductDto inventory = productService.getProductById(id);

        return ResponseEntity.ok(inventory);
    }
    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }
}
