package com.utn.api.medreminderback.model;

import lombok.Data;

import java.util.Date;

@Data
public class MedItemRequest {
    private String medicamento; // Nombre del medicamento
    private String dosis;       // Dosis
    private String frecuencia;  // Ej: "Cada X horas"
    private String horayFechaDeInicio; // Fecha y hora inicial en formato ISO (ej. 2024-11-23T12:00:00)
    private int frecuenciaEnHoras;    // Cada cuántas horas se toma
    private int cantidad;             // Número de dosis o alarmas a generar

}
