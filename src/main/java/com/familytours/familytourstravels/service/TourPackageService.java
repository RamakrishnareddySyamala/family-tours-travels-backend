package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.TourPackage;
import com.familytours.familytourstravels.repository.TourPackageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageService {

    private final TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    // Get all tour packages
    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    // Get tour package by ID
    public TourPackage getTourPackageById(Long id) {
        return tourPackageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tour package not found with id: " + id)
                );
    }

    // Create tour package
    public TourPackage createTourPackage(TourPackage tourPackage) {

        if (tourPackage.getStatus() == null ||
                tourPackage.getStatus().isBlank()) {

            tourPackage.setStatus("ACTIVE");
        }

        return tourPackageRepository.save(tourPackage);
    }

    // Update tour package
    public TourPackage updateTourPackage(
            Long id,
            TourPackage updatedTourPackage) {

        TourPackage existingPackage =
                getTourPackageById(id);

        existingPackage.setName(
                updatedTourPackage.getName()
        );

        existingPackage.setDestination(
                updatedTourPackage.getDestination()
        );

        existingPackage.setDuration(
                updatedTourPackage.getDuration()
        );

        existingPackage.setPrice(
                updatedTourPackage.getPrice()
        );

        existingPackage.setDescription(
                updatedTourPackage.getDescription()
        );

        existingPackage.setStatus(
                updatedTourPackage.getStatus()
        );

        return tourPackageRepository.save(existingPackage);
    }

    // Delete tour package
    public void deleteTourPackage(Long id) {

        if (!tourPackageRepository.existsById(id)) {
            throw new RuntimeException(
                    "Tour package not found with id: " + id
            );
        }

        tourPackageRepository.deleteById(id);
    }

    // Update package status
    public TourPackage updateTourPackageStatus(
            Long id,
            String status) {

        TourPackage tourPackage =
                getTourPackageById(id);

        tourPackage.setStatus(status);

        return tourPackageRepository.save(tourPackage);
    }
}