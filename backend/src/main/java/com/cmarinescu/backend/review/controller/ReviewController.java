package com.cmarinescu.backend.review.controller;

import com.cmarinescu.backend.review.dto.CreateReviewRequest;
import com.cmarinescu.backend.review.dto.ReviewResponse;
import com.cmarinescu.backend.review.mapper.ReviewMapper;
import com.cmarinescu.backend.review.model.Review;
import com.cmarinescu.backend.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    private ReviewService reviewService;
    public ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping("user/review")
    public ResponseEntity<ReviewResponse> addReview(@Valid @RequestBody CreateReviewRequest request){
        Review review = reviewMapper.requestToModel(request);
        ReviewResponse response = reviewMapper.modelToResponse(reviewService.addReview(review, request.getProductId()));
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("public/review")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(){
        List<Review> reviewList = reviewService.getAllReviews();
        List<ReviewResponse> responseList = reviewMapper.modelListToResponseList(reviewList);
        return ResponseEntity.ok().body(responseList);
    }
}
