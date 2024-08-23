package com.customer.order.controller;

import com.customer.order.model.Orders;
import com.customer.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    //create order
    @PostMapping("/orders")
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.saveOrder(order);
    }

    //list orders
    @GetMapping("/orders")
    public List<Orders> listOrders(@RequestParam String customerNumber) {
        return orderService.listOrders(customerNumber);
    }
}
