package org.order_service.controller;

import org.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable Long productId){
        return orderService.placeOrder(productId);
    }



}
