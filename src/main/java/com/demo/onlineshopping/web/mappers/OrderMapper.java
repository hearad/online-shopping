package com.demo.onlineshopping.web.mappers;

import com.demo.onlineshopping.domain.Order;
import com.demo.onlineshopping.domain.Review;
import com.demo.onlineshopping.web.model.OrderDto;
import com.demo.onlineshopping.web.model.ReviewDto;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderMapper {
    private DateMapper dateMapper;
    private ReviewMapper reviewMapper;
    private OrderStatusMapper orderStatusMapper;

    public OrderMapper(DateMapper dateMapper,ReviewMapper reviewMapper,OrderStatusMapper orderStatusMapper) {
        this.dateMapper = dateMapper;
        this.reviewMapper = reviewMapper;
        this.orderStatusMapper = orderStatusMapper;
    }

    public OrderDto orderToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setBuyerId(order.getBuyerId());
        orderDto.setCreatedTime(dateMapper.asOffsetDateTime(order.getCreatedTime()));
        orderDto.setLastModifiedTime(dateMapper.asOffsetDateTime(order.getLastModifiedTime()));
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setOrderStatus(orderStatusMapper.orderStatusToOrderStatusDto(order.getOrderStatus()));
        Set<ReviewDto> reviewDtos = new HashSet<>();
        if(order.getReviews()!=null) {
            for (Review review : order.getReviews()) {
                reviewDtos.add(reviewMapper.reviewToReviewDto(review));
            }
        }
        orderDto.setReviews(reviewDtos);
        return orderDto;

    }

    public Order orderDtoToOrder(OrderDto orderDto){
        Order order= new Order();
        order.setId(orderDto.getId());
        order.setBuyerId(orderDto.getBuyerId());
        order.setCreatedTime(dateMapper.asTimestamp(orderDto.getCreatedTime()));
        order.setLastModifiedTime(dateMapper.asTimestamp(orderDto.getLastModifiedTime()));
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderStatusMapper.orderStatusDtoToOrderStatus(orderDto.getOrderStatus()));
        Set<Review> reviews = new HashSet<>();
        for(ReviewDto reviewDto:orderDto.getReviews()){
            reviews.add(reviewMapper.reviewDtoToReview(reviewDto));
        }
        order.setReviews(reviews);
        return order;
    }
}
