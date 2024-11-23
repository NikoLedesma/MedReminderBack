package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.model.MedItem;

import java.util.List;
import java.util.Optional;

public interface MedItemService {

    public MedItem createMedItem(MedItem medItem);

    public List<MedItem> getMedItems();

    public Optional<MedItem> getMedItemById(Long id);

    public Optional<MedItem> updateMedItem(Long id, MedItem updatedMedItem);

    public void deleteMedItem(Long id);

}
