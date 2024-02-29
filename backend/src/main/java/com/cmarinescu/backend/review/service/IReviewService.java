package com.cmarinescu.backend.review.service;

import com.cmarinescu.backend.review.model.Review;

import java.util.List;

public interface IReviewService{
    Review addReview(Review review, Long productId);
    List<Review> getAllReviews();
}
