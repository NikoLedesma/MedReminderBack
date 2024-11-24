package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.model.MedItemRequest;
import com.utn.api.medreminderback.repository.MedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedItemServiceImpl implements MedItemService{


    private final MedItemRepository medItemRepository;
    private final MedAlarmService medAlarmService;

    @Autowired
    public MedItemServiceImpl(MedItemRepository repository,MedAlarmService medAlarmService){
        this.medItemRepository =  repository;
        this.medAlarmService = medAlarmService;
    }

    public MedItem createMedItem(MedItemRequest request) {
        // 1. Crear el MedItem
        MedItem medItem = new MedItem();
        medItem.setMedicamento(request.getMedicamento());
        medItem.setDosis(request.getDosis());
        medItem.setFrecuencia(request.getFrecuencia());
        medItem.setHoraYFechaDeInicio(LocalDateTime.parse(request.getHorayFechaDeInicio()));
        List<MedAlarm> alarms = medAlarmService.generateAlarms(request);

        // 4. Asociar alarmas al MedItem
        medItem.setAlarms(alarms);

        // 5. Guardar en la base de datos
        return medItemRepository.save(medItem);
    }



    public List<MedItem> getMedItems() {
        return medItemRepository.findAll();
    }

    public Optional<MedItem> getMedItemById(Long id) {
        return medItemRepository.findById(id);
    }

    public Optional<MedItem> updateMedItem(Long id, MedItem updatedMedItem) {
        return medItemRepository.findById(id).map(existingItem -> {
            existingItem.setMedicamento(updatedMedItem.getMedicamento());
            existingItem.setDosis(updatedMedItem.getDosis());
            existingItem.setFrecuencia(updatedMedItem.getFrecuencia());
            return medItemRepository.save(existingItem);
        });
    }

    public void deleteMedItem(Long id) {
        medItemRepository.deleteById(id);
    }

}
