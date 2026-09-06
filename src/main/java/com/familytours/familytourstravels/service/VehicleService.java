package com.familytours.familytourstravels.service;

import com.familytours.familytourstravels.entity.Vehicle;
import com.familytours.familytourstravels.exception.VehicleNotFoundException;
import com.familytours.familytourstravels.repository.VehicleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Create vehicle
    public Vehicle createVehicle(Vehicle vehicle) {

        if (vehicle.getStatus() == null ||
            vehicle.getStatus().trim().isEmpty()) {

            vehicle.setStatus("AVAILABLE");
        }

        return vehicleRepository.save(vehicle);
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    public Vehicle getVehicleById(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with ID: " + id
                        ));
    }

    // Update vehicle
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with ID: " + id
                        ));

        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setType(updatedVehicle.getType());
        existingVehicle.setSeats(updatedVehicle.getSeats());
        existingVehicle.setRegistrationNumber(
                updatedVehicle.getRegistrationNumber()
        );
        existingVehicle.setPricePerDay(
                updatedVehicle.getPricePerDay()
        );

        if (updatedVehicle.getStatus() != null) {
            existingVehicle.setStatus(updatedVehicle.getStatus());
        }

        return vehicleRepository.save(existingVehicle);
    }

    // Delete vehicle
    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {

            throw new VehicleNotFoundException(
                    "Vehicle not found with ID: " + id
            );
        }

        vehicleRepository.deleteById(id);
    }

    // Update vehicle status
    public Vehicle updateVehicleStatus(Long id, String status) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with ID: " + id
                        ));

        vehicle.setStatus(status);

        return vehicleRepository.save(vehicle);
    }
}