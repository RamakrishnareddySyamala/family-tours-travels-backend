
package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Booking;
import com.familytours.familytourstravels.repository.BookingRepository;
import com.familytours.familytourstravels.exception.BookingNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EmailServices emailServices;

    public BookingService(
            BookingRepository bookingRepository,
            EmailServices emailServices) {

        this.bookingRepository = bookingRepository;
        this.emailServices = emailServices;
    }

    // Create a new booking
    
    public Booking createBooking(Booking booking) {

        booking.setStatus("PENDING");

        // Save booking to MySQL
        Booking savedBooking = bookingRepository.save(booking);

        // Send email notification to business owner
        emailServices.sendBookingNotification(savedBooking);

        // Send confirmation email to customer
        emailServices.sendCustomerConfirmation(savedBooking);

        return savedBooking;
    }
    


    // Get all bookings
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Booking getBookingById(Long id) {

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with ID: " + id
                        ));
    }

    // Delete booking
    public void deleteBooking(Long id) {

        if (!bookingRepository.existsById(id)) {

            throw new BookingNotFoundException(
                    "Booking not found with ID: " + id
            );
        }

        bookingRepository.deleteById(id);
    }

    /// Update booking status
    public Booking updateBookingStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with ID: " + id
                        ));

        booking.setStatus(status);

        Booking updatedBooking = bookingRepository.save(booking);

        // Send confirmation email when admin confirms the booking
        if ("CONFIRMED".equalsIgnoreCase(status)) {
            emailServices.sendBookingConfirmed(updatedBooking);
        }

        return updatedBooking;
    }

    // Update booking
    public Booking updateBooking(Long id, Booking updatedBooking) {

        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with ID: " + id
                        ));

        existingBooking.setCustomerName(
                updatedBooking.getCustomerName()
        );

        existingBooking.setMobileNumber(
                updatedBooking.getMobileNumber()
        );

        existingBooking.setEmail(
                updatedBooking.getEmail()
        );

        existingBooking.setTripType(
                updatedBooking.getTripType()
        );

        existingBooking.setPickupLocation(
                updatedBooking.getPickupLocation()
        );

        existingBooking.setDestination(
                updatedBooking.getDestination()
        );

        existingBooking.setTravelDate(
                updatedBooking.getTravelDate()
        );

        existingBooking.setNumberOfPassengers(
                updatedBooking.getNumberOfPassengers()
        );

        existingBooking.setMessage(
                updatedBooking.getMessage()
        );

        return bookingRepository.save(existingBooking);
    }
}

