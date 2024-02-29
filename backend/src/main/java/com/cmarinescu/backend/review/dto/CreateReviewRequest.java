package com.cmarinescu.backend.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewRequest {
    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be positive")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer rating;

    private String content;

    @NotNull(message = "Product id is required")
    private Long productId;
}
