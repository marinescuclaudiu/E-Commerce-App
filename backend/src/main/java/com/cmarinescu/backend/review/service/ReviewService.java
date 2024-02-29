package com.cmarinescu.backend.review.service;

import com.cmarinescu.backend.common.exception.EntityNotFoundException;
import com.cmarinescu.backend.product.model.Product;
import com.cmarinescu.backend.product.repository.ProductRepository;
import com.cmarinescu.backend.review.model.Review;
import com.cmarinescu.backend.review.repository.ReviewRepository;
import com.cmarinescu.backend.user.model.User;
import com.cmarinescu.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Review addReview(Review review, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + "doesn't exists"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        review.setUser(user);
        review.setProduct(product);

        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
