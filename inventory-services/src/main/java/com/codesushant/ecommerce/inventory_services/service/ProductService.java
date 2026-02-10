package com.codesushant.ecommerce.inventory_services.service;

import com.codesushant.ecommerce.inventory_services.dto.OrderRequestDto;
import com.codesushant.ecommerce.inventory_services.dto.OrderRequestItemDto;
import com.codesushant.ecommerce.inventory_services.dto.ProductDto;
import com.codesushant.ecommerce.inventory_services.entity.Product;
import com.codesushant.ecommerce.inventory_services.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return  inventories.stream().map(product ->modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id){
        log.info("Fetching all inventory items");
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(item ->modelMapper.map(item, ProductDto.class)).orElseThrow(() -> new RuntimeException("Inventory Not Found"));
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        log.info("reducing the stocks");
        Double totalPrice=0.0;
        for(OrderRequestItemDto orderRequestItemDto: orderRequestDto.getItems()){
            Long productId = orderRequestItemDto.getProductId();
            Integer quantity = orderRequestItemDto.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found with id: "+productId));

            if(product.getStock()< quantity){
                throw  new RuntimeException("Product cannot be fulfilled for given quantity");
            }
            product.setStock(product.getStock() - quantity);
            totalPrice += quantity * product.getPrice();

        }
        return totalPrice;
    }
}
