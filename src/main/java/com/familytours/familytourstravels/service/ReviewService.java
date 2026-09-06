package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Review;
import com.familytours.familytourstravels.repository.ReviewRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // ==========================================
    // CREATE REVIEW
    // ==========================================

    public Review createReview(Review review) {

        // Default status
        if (review.getStatus() == null ||
                review.getStatus().isBlank()) {

            review.setStatus("PUBLISHED");
        }

        // Default date
        if (review.getDate() == null) {
            review.setDate(LocalDate.now());
        }

        return reviewRepository.save(review);
    }

    // ==========================================
    // GET ALL REVIEWS
    // ==========================================

    public List<Review> getAllReviews() {

        return reviewRepository.findAll();
    }

    // ==========================================
    // GET REVIEW BY ID
    // ==========================================

    public Review getReviewById(Long id) {

        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Review not found with ID: " + id
                        )
                );
    }

    // ==========================================
    // UPDATE REVIEW
    // ==========================================

    public Review updateReview(
            Long id,
            Review updatedReview) {

        Review existingReview =
                getReviewById(id);

        existingReview.setCustomerName(
                updatedReview.getCustomerName()
        );

        existingReview.setRating(
                updatedReview.getRating()
        );

        existingReview.setComment(
                updatedReview.getComment()
        );

        existingReview.setStatus(
                updatedReview.getStatus()
        );

        if (updatedReview.getDate() != null) {

            existingReview.setDate(
                    updatedReview.getDate()
            );
        }

        return reviewRepository.save(existingReview);
    }

    // ==========================================
    // DELETE REVIEW
    // ==========================================

    public void deleteReview(Long id) {

        if (!reviewRepository.existsById(id)) {

            throw new RuntimeException(
                    "Review not found with ID: " + id
            );
        }

        reviewRepository.deleteById(id);
    }

    // ==========================================
    // UPDATE REVIEW STATUS
    // ==========================================

    public Review updateReviewStatus(
            Long id,
            String status) {

        Review review =
                getReviewById(id);

        review.setStatus(status);

        return reviewRepository.save(review);
    }
}