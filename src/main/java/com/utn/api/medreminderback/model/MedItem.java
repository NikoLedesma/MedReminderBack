package com.utn.api.medreminderback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private LocalDateTime horaYFechaDeInicio;

    @Transient
    private StatusCount statusCount;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "med_item_id") // Relación unidireccional con una clave foránea en MedAlarm
    private List<MedAlarm> alarms = new ArrayList<>();
}
