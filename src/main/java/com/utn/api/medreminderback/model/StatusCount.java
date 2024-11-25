package com.utn.api.medreminderback.model;


import com.utn.api.medreminderback.utils.AlarmStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusCount {
    private Integer waitingCount;
    private Integer finishedCount;
    private Integer readyCount;


    public void incrementCount(AlarmStatus status) {
        switch (status) {
            case WAITING:
                waitingCount++;
                break;
            case FINISHED:
                finishedCount++;
                break;
            case READY:
                readyCount++;
                break;
        }
    }

    public void decrementCount(AlarmStatus status) {
        switch (status) {
            case WAITING:
                if (waitingCount > 0) waitingCount--;
                break;
            case FINISHED:
                if (finishedCount > 0) finishedCount--;
                break;
            case READY:
                if (readyCount > 0) readyCount--;
                break;
        }
    }

}
