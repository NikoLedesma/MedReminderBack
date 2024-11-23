package com.utn.api.medreminderback.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class LovServiceImpl implements LovService {

    private final Map<String, String> medicationGlossary = Map.of(
            "Paracetamol", "Usado para aliviar el dolor y reducir la fiebre.",
            "Ibuprofeno", "Usado para reducir fiebre, dolor e inflamación.",
            "Amoxicilina", "Usado para tratar infecciones bacterianas.",
            "Aspirina", "Usado para aliviar el dolor y reducir la inflamación.",
            "Loratadina", "Usado para tratar alergias, fiebre del heno y picazón."
    );

    @Override
    public String getMedicationGlossary(String medicamento) {
        return medicationGlossary.getOrDefault(medicamento, "Medicamento no encontrado.");
    }

    @Override
    public List<String> getMedicamentoNames() {
        return Arrays.asList("Paracetamol", "Ibuprofeno", "Amoxicilina", "Aspirina", "Loratadina");
    }

    @Override
    public List<String> getDoses() {
        return Arrays.asList("500mg", "400mg", "250mg", "300mg", "10mg");
    }

    @Override
    public List<String> getFrequencies() {
        return Arrays.asList("Cada 8 horas", "Cada 6 horas", "Cada 12 horas", "Cada 24 horas");
    }

    @Override
    public List<String> getStartTimes() {
        return Arrays.asList("08:00 AM", "10:00 AM", "12:00 PM", "02:00 PM", "06:00 PM");
    }

}
