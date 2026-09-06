
package com.familytours.familytourstravels.repository;

import com.familytours.familytourstravels.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}