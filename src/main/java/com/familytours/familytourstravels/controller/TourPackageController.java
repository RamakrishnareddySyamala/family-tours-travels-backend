package com.familytours.familytourstravels.controller;

import com.familytours.familytourstravels.entity.TourPackage;
import com.familytours.familytourstravels.service.TourPackageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-packages")
@CrossOrigin(origins = "*")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    // ==========================================
    // GET ALL TOUR PACKAGES
    // ==========================================
    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTourPackages() {

        return ResponseEntity.ok(
                tourPackageService.getAllTourPackages()
        );
    }

    // ==========================================
    // GET TOUR PACKAGE BY ID
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<TourPackage> getTourPackageById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tourPackageService.getTourPackageById(id)
        );
    }

    // ==========================================
    // CREATE TOUR PACKAGE
    // ==========================================
    @PostMapping
    public ResponseEntity<TourPackage> createTourPackage(
            @RequestBody TourPackage tourPackage) {

        return ResponseEntity.ok(
                tourPackageService.createTourPackage(tourPackage)
        );
    }

    // ==========================================
    // UPDATE TOUR PACKAGE
    // ==========================================
    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackage(
            @PathVariable Long id,
            @RequestBody TourPackage updatedTourPackage) {

        return ResponseEntity.ok(
                tourPackageService.updateTourPackage(
                        id,
                        updatedTourPackage
                )
        );
    }

    // ==========================================
    // DELETE TOUR PACKAGE
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourPackage(
            @PathVariable Long id) {

        tourPackageService.deleteTourPackage(id);

        return ResponseEntity.noContent().build();
    }

    // ==========================================
    // UPDATE PACKAGE STATUS
    // ==========================================
    @PutMapping("/{id}/status")
    public ResponseEntity<TourPackage> updateTourPackageStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                tourPackageService.updateTourPackageStatus(
                        id,
                        status
                )
        );
    }
}