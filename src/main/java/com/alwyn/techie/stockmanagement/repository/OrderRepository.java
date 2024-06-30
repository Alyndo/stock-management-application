package com.alwyn.techie.stockmanagement.repository;

import com.alwyn.techie.stockmanagement.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
