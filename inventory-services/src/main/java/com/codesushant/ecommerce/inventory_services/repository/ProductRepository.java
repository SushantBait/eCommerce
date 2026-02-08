package com.codesushant.ecommerce.inventory_services.repository;

import com.codesushant.ecommerce.inventory_services.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
