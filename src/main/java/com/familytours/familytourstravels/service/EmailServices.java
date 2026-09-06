
package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Booking;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {

    private final JavaMailSender mailSender;

    public EmailServices(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Email to business owner
    public void sendBookingNotification(Booking booking) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("chowtie88@gmail.com");

        message.setSubject(
                "New Booking Enquiry - Family Tours & Travels"
        );

        message.setText(
                "New Travel Enquiry Received\n\n" +

                "Booking ID: " + booking.getId() + "\n" +
                "Customer Name: " + booking.getCustomerName() + "\n" +
                "Mobile Number: " + booking.getMobileNumber() + "\n" +
                "Email: " + booking.getEmail() + "\n" +
                "Trip Type: " + booking.getTripType() + "\n" +
                "Pickup Location: " + booking.getPickupLocation() + "\n" +
                "Destination: " + booking.getDestination() + "\n" +
                "Travel Date: " + booking.getTravelDate() + "\n" +
                "Passengers: " + booking.getNumberOfPassengers() + "\n" +
                "Message: " + booking.getMessage() + "\n\n" +

                "Please contact the customer for further confirmation."
        );

        mailSender.send(message);
    }

    // Confirmation email to customer
    public void sendCustomerConfirmation(Booking booking) {

        // Don't send if customer didn't provide an email
        if (booking.getEmail() == null ||
                booking.getEmail().isBlank()) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(booking.getEmail());

        message.setSubject(
                "Booking Received - Family Tours & Travels"
        );

        message.setText(
                "Dear " + booking.getCustomerName() + ",\n\n" +

                "Thank you for choosing Family Tours & Travels.\n\n" +

                "We have received your travel booking enquiry successfully.\n\n" +

                "Booking Details\n" +
                "------------------------------\n" +
                "Booking ID: " + booking.getId() + "\n" +
                "Trip Type: " + booking.getTripType() + "\n" +
                "Pickup Location: " + booking.getPickupLocation() + "\n" +
                "Destination: " + booking.getDestination() + "\n" +
                "Travel Date: " + booking.getTravelDate() + "\n" +
                "Passengers: " + booking.getNumberOfPassengers() + "\n" +
                "Status: " + booking.getStatus() + "\n\n" +

                "Our team will contact you shortly to confirm your booking.\n\n" +

                "Thank you,\n" +
                "Family Tours & Travels"
        );

        mailSender.send(message);
    }
}

