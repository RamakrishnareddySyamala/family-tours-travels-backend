package com.familytours.familytourstravels.controller;

import com.familytours.familytourstravels.entity.Review;
import com.familytours.familytourstravels.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ==========================================
    // CREATE REVIEW
    // ==========================================

    @PostMapping
    public ResponseEntity<Review> createReview(
            @Valid @RequestBody Review review) {

        Review savedReview =
                reviewService.createReview(review);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedReview);
    }

    // ==========================================
    // GET ALL REVIEWS
    // ==========================================

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {

        return ResponseEntity.ok(
                reviewService.getAllReviews()
        );
    }

    // ==========================================
    // GET REVIEW BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewService.getReviewById(id)
        );
    }

    // ==========================================
    // UPDATE REVIEW
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody Review updatedReview) {

        return ResponseEntity.ok(
                reviewService.updateReview(
                        id,
                        updatedReview
                )
        );
    }

    // ==========================================
    // DELETE REVIEW
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    // ==========================================
    // UPDATE REVIEW STATUS
    // ==========================================

    @PutMapping("/{id}/status")
    public ResponseEntity<Review> updateReviewStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                reviewService.updateReviewStatus(
                        id,
                        status
                )
        );
    }
}