package com.demo.onlineshopping.web.mappers;

import com.demo.onlineshopping.domain.Review;
import com.demo.onlineshopping.repositories.OrderRepository;
import com.demo.onlineshopping.web.model.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private DateMapper dateMapper;
    private OrderRepository orderRepository;

    public ReviewMapper(DateMapper dateMapper, OrderRepository orderRepository) {
        this.dateMapper = dateMapper;
        this.orderRepository = orderRepository;
    }

    public ReviewDto reviewToReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setClientName(review.getClientName());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setCreatedTime(dateMapper.asOffsetDateTime(review.getCreatedTime()));
        reviewDto.setLastModifiedTime(dateMapper.asOffsetDateTime(review.getLastModifiedTime()));
        reviewDto.setOrderId(review.getOrder().getId());
        return reviewDto;
    }

    public Review reviewDtoToReview(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setClientName(reviewDto.getClientName());
        review.setDescription(reviewDto.getDescription());
        review.setTitle(reviewDto.getTitle());
        review.setCreatedTime(dateMapper.asTimestamp(reviewDto.getCreatedTime()));
        review.setLastModifiedTime(dateMapper.asTimestamp(reviewDto.getLastModifiedTime()));
        if(reviewDto.getOrderId() != null)
            review.setOrder(orderRepository.getOne(reviewDto.getOrderId()));
        return review;
    }
}
