package com.utn.api.medreminderback.controller;


import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.service.MedItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meditems")
public class MedItemController {



    private final MedItemServiceImpl service;

    @Autowired
    public MedItemController(MedItemServiceImpl service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<MedItem> createMedItem(@RequestBody MedItem medItem) {
        return new ResponseEntity<>(service.createMedItem(medItem), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MedItem> getMedItems() {
        return service.getMedItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedItem> getMedItemById(@PathVariable Long id) {
        return service.getMedItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedItem> updateMedItem(@PathVariable Long id, @RequestBody MedItem updatedMedItem) {
        return service.updateMedItem(id, updatedMedItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedItem(@PathVariable Long id) {
        service.deleteMedItem(id);
        return ResponseEntity.noContent().build();
    }
}
