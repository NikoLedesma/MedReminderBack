package com.utn.api.medreminderback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dayOfWeek; // 1 (domingo) a 7 (sábado)
    private int alarmHour;      // Hora en formato 24 horas
    private int alarmMinute;    // Minuto

}