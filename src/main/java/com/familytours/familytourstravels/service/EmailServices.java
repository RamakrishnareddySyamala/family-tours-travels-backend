package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Booking;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {

    private final Resend resend;

    public EmailServices(
            @Value("${RESEND_API_KEY}") String resendApiKey) {

        this.resend = new Resend(resendApiKey);
    }

    // Email to business owner
    public void sendBookingNotification(Booking booking) {

        String emailText =
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
                "Please contact the customer for further confirmation.";

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Family Tours & Travels <onboarding@resend.dev>")
                .to("chowtie88@gmail.com")
                .subject("New Booking Enquiry - Family Tours & Travels")
                .text(emailText)
                .build();

        try {
            resend.emails().send(params);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to send booking notification email", e);
        }
    }

    // Confirmation email to customer
    public void sendCustomerConfirmation(Booking booking) {

        if (booking.getEmail() == null ||
                booking.getEmail().isBlank()) {
            return;
        }

        String emailText =
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
                "Family Tours & Travels";

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Family Tours & Travels <onboarding@resend.dev>")
                .to(booking.getEmail())
                .subject("Booking Received - Family Tours & Travels")
                .text(emailText)
                .build();

        try {
            resend.emails().send(params);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to send customer confirmation email", e);
        }
    }
}