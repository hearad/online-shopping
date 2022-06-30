package com.demo.onlineshopping.services;

import com.demo.onlineshopping.domain.Review;
import com.demo.onlineshopping.repositories.ReviewRepository;
import com.demo.onlineshopping.web.mappers.ReviewMapper;
import com.demo.onlineshopping.web.model.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;


    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return reviewMapper.reviewToReviewDto(reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        review.getOrder().getReviews().add(review);

        Review savedReview = reviewRepository.save(review);

        ReviewDto savedReviewDto = reviewMapper.reviewToReviewDto(savedReview);
        log.info("Review has been created.");
        return savedReviewDto;
    }

    @Transactional
    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        review.setId(id);
        ReviewDto updatedReviewDto = reviewMapper.reviewToReviewDto(reviewRepository.save(review));
        log.info("Review has been updated.");
        return updatedReviewDto;
    }

    @Transactional
    @Override
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
        log.info("Review has been deleted.");
    }
}
