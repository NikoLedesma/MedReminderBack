package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.repository.MedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedItemServiceImpl implements MedItemService{


    private final MedItemRepository medItemRepository;

    @Autowired
    public MedItemServiceImpl(MedItemRepository repository){
        this.medItemRepository =  repository;
    }

    public MedItem createMedItem(MedItem medItem) {

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
