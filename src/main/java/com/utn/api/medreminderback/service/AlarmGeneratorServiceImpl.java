package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItemRequest;
import com.utn.api.medreminderback.utils.AlarmStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmGeneratorServiceImpl implements AlarmGeneratorService {
    @Override
    public List<MedAlarm> generateAlarms(MedItemRequest request) {
        LocalDateTime startDateTime = LocalDateTime.parse(request.getHorayFechaDeInicio());
        List<MedAlarm> alarms = new ArrayList<>();
        for (int i = 0; i < request.getCantidad(); i++) {
            MedAlarm alarm = new MedAlarm();
            alarm.setAlarmDateTime(startDateTime.plusHours(i * request.getFrecuenciaEnHoras()));
            alarm.setAlarmHour(alarm.getAlarmDateTime().getHour());
            alarm.setAlarmMinute(alarm.getAlarmDateTime().getMinute());
            alarm.setDayOfWeek(alarm.getAlarmDateTime().getDayOfWeek().getValue());
            alarm.setStatus(AlarmStatus.WAITING.asChar());
            alarms.add(alarm);
        }
        return alarms;
    }
}
