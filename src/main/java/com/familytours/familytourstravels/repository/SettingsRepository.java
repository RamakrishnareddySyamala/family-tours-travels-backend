
package com.familytours.familytourstravels.repository;

import com.familytours.familytourstravels.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}