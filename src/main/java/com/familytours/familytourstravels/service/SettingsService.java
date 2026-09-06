
package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Settings;
import com.familytours.familytourstravels.repository.SettingsRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings getSettings() {

        return settingsRepository
                .findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {

                    Settings settings = new Settings();

                    settings.setCompanyName("Family Tours & Travels");
                    settings.setOpeningTime("06:00");
                    settings.setClosingTime("22:00");

                    settings.setOnlineBooking(true);
                    settings.setWhatsappBooking(true);
                    settings.setCallBooking(true);

                    settings.setEmailNotifications(true);
                    settings.setBookingNotifications(true);
                    settings.setReviewNotifications(true);

                    return settingsRepository.save(settings);
                });
    }

    public Settings updateSettings(Settings updatedSettings) {

        Settings existingSettings = getSettings();

        existingSettings.setCompanyName(
                updatedSettings.getCompanyName()
        );

        existingSettings.setPhoneNumber(
                updatedSettings.getPhoneNumber()
        );

        existingSettings.setEmail(
                updatedSettings.getEmail()
        );

        existingSettings.setWhatsappNumber(
                updatedSettings.getWhatsappNumber()
        );

        existingSettings.setAddress(
                updatedSettings.getAddress()
        );

        existingSettings.setCity(
                updatedSettings.getCity()
        );

        existingSettings.setState(
                updatedSettings.getState()
        );

        existingSettings.setPincode(
                updatedSettings.getPincode()
        );

        existingSettings.setOpeningTime(
                updatedSettings.getOpeningTime()
        );

        existingSettings.setClosingTime(
                updatedSettings.getClosingTime()
        );

        existingSettings.setOnlineBooking(
                updatedSettings.isOnlineBooking()
        );

        existingSettings.setWhatsappBooking(
                updatedSettings.isWhatsappBooking()
        );

        existingSettings.setCallBooking(
                updatedSettings.isCallBooking()
        );

        existingSettings.setEmailNotifications(
                updatedSettings.isEmailNotifications()
        );

        existingSettings.setBookingNotifications(
                updatedSettings.isBookingNotifications()
        );

        existingSettings.setReviewNotifications(
                updatedSettings.isReviewNotifications()
        );

        return settingsRepository.save(existingSettings);
    }
}
