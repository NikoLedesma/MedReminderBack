package com.utn.api.medreminderback.repository;

import com.utn.api.medreminderback.model.MedAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedAlarmRepository extends JpaRepository<MedAlarm, Long> {
}
