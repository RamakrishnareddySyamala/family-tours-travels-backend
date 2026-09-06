package com.familytours.familytourstravels.controller;



import com.familytours.familytourstravels.dto.BookingRequest;
import com.familytours.familytourstravels.dto.BookingResponse;
import com.familytours.familytourstravels.entity.Booking;
import com.familytours.familytourstravels.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Create booking
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request) {

        Booking booking = new Booking();

        booking.setCustomerName(request.getCustomerName());
        booking.setMobileNumber(request.getMobileNumber());
        booking.setEmail(request.getEmail());
        booking.setTripType(request.getTripType());
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDestination(request.getDestination());
        booking.setTravelDate(request.getTravelDate());
        booking.setNumberOfPassengers(request.getNumberOfPassengers());
        booking.setMessage(request.getMessage());

        Booking savedBooking = bookingService.createBooking(booking);

        BookingResponse response = new BookingResponse();

        response.setId(savedBooking.getId());
        response.setCustomerName(savedBooking.getCustomerName());
        response.setMobileNumber(savedBooking.getMobileNumber());
        response.setEmail(savedBooking.getEmail());
        response.setTripType(savedBooking.getTripType());
        response.setPickupLocation(savedBooking.getPickupLocation());
        response.setDestination(savedBooking.getDestination());
        response.setTravelDate(savedBooking.getTravelDate());
        response.setNumberOfPassengers(savedBooking.getNumberOfPassengers());
        response.setMessage(savedBooking.getMessage());
        response.setStatus(savedBooking.getStatus());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );
    }

    // Update booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody Booking updatedBooking) {

        Booking booking = bookingService.updateBooking(
                id,
                updatedBooking
        );

        return ResponseEntity.ok(booking);
    }

    // Delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Booking booking = bookingService.updateBookingStatus(id, status);

        return ResponseEntity.ok(booking);
    }
    
}