package com.alwyn.techie.stockmanagement.controller;

import com.alwyn.techie.stockmanagement.exception.ResourceNotFoundException;
import com.alwyn.techie.stockmanagement.model.Orders;
import com.alwyn.techie.stockmanagement.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders(){
        logger.info("Getting all orders");
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable(value = "id") Long orderId){
        logger.info("Getting order by id: {}", orderId);
        Orders orders = orderService.getOrderById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id ::" + orderId));

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping
    public Orders createOrder(@Valid @RequestBody Orders orders){
        logger.info("Creating new order");
        return orderService.createOrder(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable(value = "id") Long orderId,
                                              @Valid @RequestBody Orders ordersDetails){
        logger.info("Updating order with id: {}", orderId);
        Orders updatedOrders = orderService.updateOrder(orderId, ordersDetails);
        return ResponseEntity.ok(updatedOrders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") Long orderId){
        logger.info("Deleting order with id: {}", orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
