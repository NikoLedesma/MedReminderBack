package com.utn.api.medreminderback.utils;

public enum AlarmStatus {
    WAITING('W'),FINISHED('F');
    private final char asChar;
    public char asChar() {
        return asChar;
    }
    AlarmStatus(char asChar){
        this.asChar=asChar;
    }
}
