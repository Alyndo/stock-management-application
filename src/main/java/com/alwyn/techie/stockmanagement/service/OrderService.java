package com.alwyn.techie.stockmanagement.service;

import com.alwyn.techie.stockmanagement.exception.ResourceNotFoundException;
import com.alwyn.techie.stockmanagement.model.Orders;
import com.alwyn.techie.stockmanagement.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders(){
        logger.info("Fetching all orders");
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id){
        logger.info("Fetching order by id: {}", id);
        return orderRepository.findById(id);
    }

    public Orders createOrder(Orders orders){
        logger.info("Creating new order");
        return orderRepository.save(orders);
    }

    public Orders updateOrder(Long id, Orders ordersDetails){
        logger.info("Updating order with id: {}", id);
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));

        orders.setOrderDate(ordersDetails.getOrderDate());
        orders.setProducts(ordersDetails.getProducts());

        return orderRepository.save(orders);
    }

    public void deleteOrder(Long id){
        logger.info("Deleting order with id: {}", id);
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));

        orderRepository.delete(orders);
    }
}
