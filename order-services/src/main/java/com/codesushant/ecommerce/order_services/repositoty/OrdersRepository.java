package com.codesushant.ecommerce.order_services.repositoty;

import com.codesushant.ecommerce.order_services.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository <Orders, Long>{
}
