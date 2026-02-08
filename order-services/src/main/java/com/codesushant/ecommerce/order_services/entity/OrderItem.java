package com.codesushant.ecommerce.order_services.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private Long productId;
     private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

}
