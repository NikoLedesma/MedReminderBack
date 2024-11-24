package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItemRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface MedAlarmService {
    List<MedAlarm> generateAlarms(MedItemRequest request);
}
