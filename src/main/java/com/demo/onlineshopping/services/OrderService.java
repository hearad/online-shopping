package com.demo.onlineshopping.services;

import com.demo.onlineshopping.web.model.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    List<OrderDto> getAllOrders(Long buyerId);

    OrderDto placeOrder(OrderDto orderDto);

}
