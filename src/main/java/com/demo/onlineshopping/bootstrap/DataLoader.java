package com.demo.onlineshopping.bootstrap;

import com.demo.onlineshopping.domain.Order;
import com.demo.onlineshopping.domain.OrderStatus;
import com.demo.onlineshopping.domain.Review;
import com.demo.onlineshopping.services.OrderService;
import com.demo.onlineshopping.services.ReviewService;
import com.demo.onlineshopping.web.mappers.OrderMapper;
import com.demo.onlineshopping.web.mappers.ReviewMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;


@Component
public class DataLoader implements CommandLineRunner {

    private final OrderService orderService;
    private final ReviewService reviewService;

    private final OrderMapper orderMapper;
    private final ReviewMapper reviewMapper;

    public DataLoader(OrderService orderService, ReviewService reviewService, OrderMapper orderMapper, ReviewMapper reviewMapper) {
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.orderMapper = orderMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = orderService.getAllOrders().size();

        if (count == 0 ){
            loadData();
        }
    }

    private void loadData() {

        Order order1 = new Order();
        order1.setBuyerId(50L);
        order1.setOrderStatus(OrderStatus.CONFIRMED);
        order1.setTotalAmount(new BigDecimal(50));

        Review review1 = Review.builder().clientName("Jack").order(order1)
                .description("This is the description for review Jack").title("Review title Jack").build();
        Review review2 = Review.builder().clientName("Tom").order(order1)
                .description("This is the description for review Tom").title("Review title Tom").build();

        HashSet<Review> reviews = new HashSet<>();
        reviews.add(review1);
        reviews.add(review2);

        order1.setReviews(reviews);

        orderService.placeOrder(orderMapper.orderToOrderDto(order1));

        Order order2 = Order.builder().buyerId(11L).orderStatus(OrderStatus.CONFIRMED)
                .totalAmount(new BigDecimal(51)).build();
        orderService.placeOrder(orderMapper.orderToOrderDto(order2));

        Order order3 = Order.builder().buyerId(12L).orderStatus(OrderStatus.CONFIRMED)
                .totalAmount(new BigDecimal(52)).build();
        orderService.placeOrder(orderMapper.orderToOrderDto(order3));

    }
}
