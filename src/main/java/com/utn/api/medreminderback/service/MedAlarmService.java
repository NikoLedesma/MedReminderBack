package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.model.MedItemRequest;

import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MedAlarmService {
    List<MedAlarm> generateAlarms(MedItemRequest request);

    Optional<MedAlarm> alarmToFinish(Long id);

    Optional<MedAlarm> getNextAlarmById(Long id);

    Optional<MedAlarm> alarmToReady(Long id);
}
