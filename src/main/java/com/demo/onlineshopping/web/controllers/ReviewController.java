package com.demo.onlineshopping.web.controllers;

import com.demo.onlineshopping.services.ReviewService;
import com.demo.onlineshopping.web.model.ReviewDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable("id") Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id , @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable Long id){
        reviewService.deleteReviewById(id);
    }


}
