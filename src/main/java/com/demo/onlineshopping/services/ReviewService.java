package com.demo.onlineshopping.services;

import com.demo.onlineshopping.web.model.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto updateReview(Long id, ReviewDto reviewDto);

    void deleteReviewById(Long id);

}
