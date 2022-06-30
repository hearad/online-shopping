package com.demo.onlineshopping.repositories;


import com.demo.onlineshopping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByBuyerId(Long buyerId);

}
