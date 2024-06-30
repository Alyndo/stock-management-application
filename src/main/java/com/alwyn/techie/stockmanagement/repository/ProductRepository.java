package com.alwyn.techie.stockmanagement.repository;

import com.alwyn.techie.stockmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
