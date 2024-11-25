package com.utn.api.medreminderback.service;


import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.model.MedItemRequest;
import com.utn.api.medreminderback.repository.MedAlarmRepository;
import com.utn.api.medreminderback.repository.MedItemRepository;
import com.utn.api.medreminderback.utils.AlarmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MedAlarmServiceImpl implements MedAlarmService {

    private MedAlarmRepository medAlarmRepository;
    private MedItemService medItemService;

    @Autowired
    public MedAlarmServiceImpl (MedAlarmRepository medAlarmRepository,MedItemService medItemService) {
        this.medAlarmRepository=medAlarmRepository;
        this.medItemService=medItemService;
    }




    @Override
    public  List<MedAlarm> generateAlarms(MedItemRequest request) {
        // 2. Convertir hora y fecha de inicio
        LocalDateTime startDateTime = LocalDateTime.parse(request.getHorayFechaDeInicio());

        // 3. Generar alarmas y guardar
        List<MedAlarm> alarms = new ArrayList<>();
        for (int i = 0; i < request.getCantidad(); i++) {
            MedAlarm alarm = new MedAlarm();
            alarm.setAlarmDateTime(startDateTime.plusSeconds(i * request.getFrecuenciaEnSegundos())); // Sumar horas
            alarm.setAlarmHour(alarm.getAlarmDateTime().getHour());
            alarm.setAlarmMinute(alarm.getAlarmDateTime().getMinute());
            alarm.setDayOfWeek(alarm.getAlarmDateTime().getDayOfWeek().getValue());
            //if (i==0){
            //    alarm.setStatus(AlarmStatus.READY.asChar());
            //} else{
                alarm.setStatus(AlarmStatus.WAITING.asChar());
            //}


            alarms.add(alarm);
        }
        return alarms;
    }

    @Override
    public Optional<MedAlarm> alarmToFinish(Long id) {
       return  this.medAlarmRepository.findById(id).map(
                existingItem->{
                    existingItem.setStatus(AlarmStatus.FINISHED.asChar());
                    return medAlarmRepository.save(existingItem);

                }
        );
    }


    @Override
    public Optional<MedAlarm> alarmToReady(Long id) {
        return  this.medAlarmRepository.findById(id).map(
                existingItem->{
                    existingItem.setStatus(AlarmStatus.READY.asChar());
                    return medAlarmRepository.save(existingItem);

                }
        );
    }

    @Override
    public Optional<MedAlarm> getNextAlarmById(Long id) {

        Optional<MedItem> optionalMedItem = medItemService.getMedItemById(id);
        if (optionalMedItem.isPresent()) {
            MedItem medItem = optionalMedItem.get();
            Optional<MedAlarm> nextAlarm = medItem.getAlarms().stream()
                    .filter(alarm -> alarm.getStatus() == AlarmStatus.WAITING.asChar())
                    .sorted(Comparator.comparing(MedAlarm::getId))
                    .findFirst();
            return nextAlarm;
        } else {
            return  null;
        }

    }

}
