package com.utn.api.medreminderback.controller;


import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.service.MedAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alarm-med-items")
public class AlarmMedController {


    private final MedAlarmService medAlarmService;

    @Autowired
    public AlarmMedController(MedAlarmService medAlarmService) {
        this.medAlarmService = medAlarmService;
    }

    @PutMapping("/toFinishStatus/{id}")
    public ResponseEntity<MedAlarm> alarmToFinish(@PathVariable Long id){
        return medAlarmService.alarmToFinish(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
