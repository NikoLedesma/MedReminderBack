package com.utn.api.medreminderback.repository;

import com.utn.api.medreminderback.model.MedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedItemRepository extends JpaRepository<MedItem, Long> {
}
