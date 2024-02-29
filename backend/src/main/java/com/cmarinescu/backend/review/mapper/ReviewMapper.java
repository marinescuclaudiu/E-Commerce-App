package com.cmarinescu.backend.review.mapper;

import com.cmarinescu.backend.review.dto.CreateReviewRequest;
import com.cmarinescu.backend.review.dto.ReviewResponse;
import com.cmarinescu.backend.review.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {
    public Review requestToModel(CreateReviewRequest request) {
        return new Review(request.getRating(), request.getContent());
    }

    public ReviewResponse modelToResponse(Review review){
        return new ReviewResponse(
                review.getId(),
                review.getRating(),
                review.getDate(),
                review.getContent(),
                review.getUser().getEmail(),
                review.getProduct().getId());
    }

    public List<ReviewResponse> modelListToResponseList(List<Review> reviewList) {
        List<ReviewResponse> responseList = new ArrayList<>();
        for(Review review : reviewList){
            responseList.add(modelToResponse(review));
        }
        return responseList;
    }
}
