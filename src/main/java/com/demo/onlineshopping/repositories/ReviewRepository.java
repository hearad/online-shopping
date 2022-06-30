package com.demo.onlineshopping.repositories;

import com.demo.onlineshopping.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByOrderId(Long orderId);

}
