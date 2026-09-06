package com.familytours.familytourstravels.repository;

import com.familytours.familytourstravels.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}