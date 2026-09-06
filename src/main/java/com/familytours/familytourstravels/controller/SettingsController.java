package com.familytours.familytourstravels.controller;

import com.familytours.familytourstravels.entity.Settings;
import com.familytours.familytourstravels.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "http://localhost:5173")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public ResponseEntity<Settings> getSettings() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    @PutMapping
    public ResponseEntity<Settings> updateSettings(
            @RequestBody Settings settings) {

        return ResponseEntity.ok(
                settingsService.updateSettings(settings)
        );
    }
}