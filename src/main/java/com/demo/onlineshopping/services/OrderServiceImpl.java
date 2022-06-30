package com.demo.onlineshopping.services;

import com.demo.onlineshopping.domain.Order;
import com.demo.onlineshopping.repositories.OrderRepository;
import com.demo.onlineshopping.web.mappers.OrderMapper;
import com.demo.onlineshopping.web.model.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrders(Long buyerId) {
        return orderRepository.findAllByBuyerId(buyerId)
                .stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id){
        return orderMapper.orderToOrderDto(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        if (order.getReviews() != null)
            order.getReviews().forEach(review -> review.setOrder(order));

        OrderDto placedOrderDto = orderMapper.orderToOrderDto(orderRepository.save(order));
        log.info("Order has been placed.");
        return placedOrderDto;
    }
}
