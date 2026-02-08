package com.codesushant.ecommerce.order_services.controller;

import com.codesushant.ecommerce.order_services.dto.OrderRequestDto;
import com.codesushant.ecommerce.order_services.entity.Orders;
import com.codesushant.ecommerce.order_services.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/orders"))
@RequiredArgsConstructor
@Slf4j
public class orderController {
    private final OrdersService ordersService;

    @GetMapping("/helloOrder")
    public String HelloOrders(){
        return "Hello from order services";
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/id")
    public  ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);
        OrderRequestDto order=ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
