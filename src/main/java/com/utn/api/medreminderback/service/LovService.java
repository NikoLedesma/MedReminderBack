package com.utn.api.medreminderback.service;

import java.util.Arrays;
import java.util.List;

public interface LovService {

    String getMedicationGlossary(String medicamento);

    public List<String> getMedicamentoNames();

    public List<String> getDoses();

    public List<String> getFrequencies();

    public List<String> getStartTimes();

}
