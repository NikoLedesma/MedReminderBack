package com.utn.api.medreminderback.controller;


import com.utn.api.medreminderback.service.LovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lov")
public class LovController {


    @Autowired
    private LovService lovService;

    // Obtener solo los nombres de los medicamentos
    @GetMapping("/medications")
    public List<String> getMedicamentoNames() {
        return lovService.getMedicamentoNames();
    }

    // Obtener solo las dosis
    @GetMapping("/doses")
    public List<String> getDoses() {
        return lovService.getDoses();
    }

    // Obtener solo las frecuencias
    @GetMapping("/frequencies")
    public List<String> getFrequencies() {
        return lovService.getFrequencies();
    }

    // Obtener solo las horas de inicio
    @GetMapping("/start-times")
    public List<String> getStartTimes() {
        return lovService.getStartTimes();
    }



    // Get the medication indication based on its name
    @GetMapping("/medication-glossary")
    public String getMedicationGlossary(@RequestParam("medication") String medication) {
        return lovService.getMedicationGlossary(medication);
    }


}
