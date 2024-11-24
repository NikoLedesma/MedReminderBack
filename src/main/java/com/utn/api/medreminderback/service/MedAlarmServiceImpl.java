package com.utn.api.medreminderback.service;


import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItemRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedAlarmServiceImpl implements MedAlarmService {


    @Override
    public  List<MedAlarm> generateAlarms(MedItemRequest request) {
        // 2. Convertir hora y fecha de inicio
        LocalDateTime startDateTime = LocalDateTime.parse(request.getHorayFechaDeInicio());

        // 3. Generar alarmas y guardar
        List<MedAlarm> alarms = new ArrayList<>();
        for (int i = 0; i < request.getCantidad(); i++) {
            MedAlarm alarm = new MedAlarm();
            alarm.setAlarmDateTime(startDateTime.plusHours(i * request.getFrecuenciaEnHoras())); // Sumar horas
            alarm.setAlarmHour(alarm.getAlarmDateTime().getHour());
            alarm.setAlarmMinute(alarm.getAlarmDateTime().getMinute());
            alarm.setDayOfWeek(alarm.getAlarmDateTime().getDayOfWeek().getValue());
            alarms.add(alarm);
        }
        return alarms;
    }

}
