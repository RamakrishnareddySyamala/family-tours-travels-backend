
package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Booking;
import com.familytours.familytourstravels.repository.BookingRepository;
import com.familytours.familytourstravels.exception.BookingNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private static final Logger logger =
            LoggerFactory.getLogger(BookingService.class);

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

        logger.info(
                "Booking saved successfully. Booking ID: {}",
                savedBooking.getId()
        );

        // Send email notifications
        try {

            // Email to business owner
            emailServices.sendBookingNotification(savedBooking);

            logger.info(
                    "Business owner notification email sent for booking ID: {}",
                    savedBooking.getId()
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to send business owner notification for booking ID: {}",
                    savedBooking.getId(),
                    e
            );
        }

        try {

            // Confirmation email to customer
            emailServices.sendCustomerConfirmation(savedBooking);

            logger.info(
                    "Customer confirmation email sent for booking ID: {}",
                    savedBooking.getId()
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to send customer confirmation email for booking ID: {}",
                    savedBooking.getId(),
                    e
            );
        }

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

    // Update booking status
    public Booking updateBookingStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with ID: " + id
                        ));

        booking.setStatus(status);

        // Save updated status to MySQL
        Booking updatedBooking = bookingRepository.save(booking);

        logger.info(
                "Booking ID: {} status updated to {}",
                updatedBooking.getId(),
                updatedBooking.getStatus()
        );

        // Send confirmation email only when booking is confirmed
        if ("CONFIRMED".equalsIgnoreCase(status)) {

            try {

                emailServices.sendBookingConfirmed(updatedBooking);

                logger.info(
                        "Booking confirmation email sent to customer for booking ID: {}",
                        updatedBooking.getId()
                );

            } catch (Exception e) {

                logger.error(
                        "Failed to send booking confirmation email for booking ID: {}",
                        updatedBooking.getId(),
                        e
                );
            }
        }

        return updatedBooking;
    }

    // Update booking
    public Booking updateBooking(
            Long id,
            Booking updatedBooking) {

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
