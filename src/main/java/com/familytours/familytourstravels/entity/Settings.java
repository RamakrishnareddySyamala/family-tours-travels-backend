
package com.familytours.familytourstravels.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String phoneNumber;

    private String email;

    private String whatsappNumber;

    @Column(length = 500)
    private String address;

    private String city;

    private String state;

    private String pincode;

    private String openingTime;

    private String closingTime;

    private boolean onlineBooking;

    private boolean whatsappBooking;

    private boolean callBooking;

    private boolean emailNotifications;

    private boolean bookingNotifications;

    private boolean reviewNotifications;

    public Settings() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isOnlineBooking() {
        return onlineBooking;
    }

    public void setOnlineBooking(boolean onlineBooking) {
        this.onlineBooking = onlineBooking;
    }

    public boolean isWhatsappBooking() {
        return whatsappBooking;
    }

    public void setWhatsappBooking(boolean whatsappBooking) {
        this.whatsappBooking = whatsappBooking;
    }

    public boolean isCallBooking() {
        return callBooking;
    }

    public void setCallBooking(boolean callBooking) {
        this.callBooking = callBooking;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public boolean isBookingNotifications() {
        return bookingNotifications;
    }

    public void setBookingNotifications(boolean bookingNotifications) {
        this.bookingNotifications = bookingNotifications;
    }

    public boolean isReviewNotifications() {
        return reviewNotifications;
    }

    public void setReviewNotifications(boolean reviewNotifications) {
        this.reviewNotifications = reviewNotifications;
    }
}
