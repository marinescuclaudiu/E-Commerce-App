package com.cmarinescu.backend.review.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Integer rating;
    private LocalDate date;
    private String content;
    private String email;
    private Long productId;
}
