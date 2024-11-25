package com.utn.api.medreminderback.service;

import com.utn.api.medreminderback.exception.MedAlreadyExisteException;
import com.utn.api.medreminderback.model.MedAlarm;
import com.utn.api.medreminderback.model.MedItem;
import com.utn.api.medreminderback.model.MedItemRequest;
import com.utn.api.medreminderback.model.StatusCount;
import com.utn.api.medreminderback.repository.MedItemRepository;
import com.utn.api.medreminderback.utils.AlarmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedItemServiceImpl implements MedItemService {


    private final MedItemRepository medItemRepository;
    private final AlarmGeneratorService alarmGeneratorService;

    @Autowired
    public MedItemServiceImpl(MedItemRepository repository, AlarmGeneratorService alarmGeneratorService) {
        this.medItemRepository = repository;
        this.alarmGeneratorService = alarmGeneratorService;
    }

    public MedItem createMedItem(MedItemRequest request) {
        List<MedItem> items =
                medItemRepository.findByMedicamento(request.getMedicamento())
                        .stream()
                        .peek(medItem -> {
                            StatusCount statusCount = new StatusCount(0, 0, 0);
                            medItem.getAlarms().forEach(alarm -> {
                                AlarmStatus status = AlarmStatus.fromChar(alarm.getStatus());
                                statusCount.incrementCount(status);
                            });
                            medItem.setStatusCount(statusCount);
                        }).filter(medItem -> {
                            return medItem.getStatusCount().getReadyCount()!=0 && medItem.getStatusCount().getWaitingCount()!=0;
                        })
                        .collect(Collectors.toList());
        if(items!=null && items.size()>0){
            throw new MedAlreadyExisteException("Ya existe una programación pendiente para el medicamento "+request.getMedicamento()+"\nEn caso de necesitar crear una nueva programacion previamente se debe eliminar la programacion pendiente");
        }





        // 1. Crear el MedItem
        MedItem medItem = new MedItem();
        medItem.setMedicamento(request.getMedicamento());
        medItem.setDosis(request.getDosis());
        medItem.setFrecuencia(request.getFrecuencia());
        medItem.setHoraYFechaDeInicio(LocalDateTime.parse(request.getHorayFechaDeInicio()));
        List<MedAlarm> alarms = alarmGeneratorService.generateAlarms(request);

        // 4. Asociar alarmas al MedItem
        medItem.setAlarms(alarms);

        // 5. Guardar en la base de datos
        return medItemRepository.save(medItem);
    }


    public List<MedItem> getMedItems() {
        return medItemRepository.findAll().stream()
                .peek(medItem -> {
                    StatusCount statusCount = new StatusCount(0, 0, 0);
                    medItem.getAlarms().forEach(alarm -> {
                        AlarmStatus status = AlarmStatus.fromChar(alarm.getStatus());
                        statusCount.incrementCount(status);
                    });
                    medItem.setStatusCount(statusCount);
                })
                .collect(Collectors.toList());
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
