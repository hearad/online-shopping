package com.demo.onlineshopping.web.controllers;

import com.demo.onlineshopping.services.OrderService;
import com.demo.onlineshopping.web.model.OrderDto;
import com.demo.onlineshopping.web.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping({"", "/"})
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/buyer/{id}")
    public List<OrderDto> getOrdersByBuyerId(@PathVariable("id") Long buyerId) {
        return orderService.getAllOrders(buyerId);
    }

    @GetMapping("/{id}/review")
    public Set<ReviewDto> getReviewsByOrderId(@PathVariable("id") Long orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        return order.getReviews();
    }

}
