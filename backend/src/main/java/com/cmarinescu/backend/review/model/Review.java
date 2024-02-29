package com.cmarinescu.backend.review.model;

import com.cmarinescu.backend.product.model.Product;
import com.cmarinescu.backend.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be positive")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Column(nullable = false)
    private Integer rating;

    @NotNull
    @PastOrPresent(message = "The creation date cannot be in the future")
    @Column(nullable = false)
    private LocalDate date;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(){

    }

    public Review(Integer rating, String content) {
        this.rating = rating;
        this.content = content;
        this.date = LocalDate.now();
    }
}
