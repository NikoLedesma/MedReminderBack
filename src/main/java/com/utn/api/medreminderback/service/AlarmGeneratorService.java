package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItemRequest;

import java.util.List;

public interface AlarmGeneratorService {

    List<MedAlarm> generateAlarms(MedItemRequest request);
    }
