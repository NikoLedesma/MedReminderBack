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

    @PutMapping("/toReadyStatus/{id}")
    public ResponseEntity<MedAlarm> alarmToReady(@PathVariable Long id){
        return medAlarmService.alarmToReady(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/next-alarm-by-medId/{id}")
    public ResponseEntity<MedAlarm> getNextAlarmById(@PathVariable Long id) {
        return medAlarmService.getNextAlarmById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok().build()); // Devuelve 200 OK con cuerpo vacío
    }

    //De la lista de alarmas agarrar la alarma que tiene fecha mas proxima y este en estado de waiting
    //Con la alarma se debera llamar al alarm Scheduler y activar la alarma

}
