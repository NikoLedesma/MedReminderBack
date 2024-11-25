package com.utn.api.medreminderback.utils;

public enum AlarmStatus {
    WAITING('W'),FINISHED('F'),READY('R');
    private final char asChar;
    public char asChar() {
        return asChar;
    }
    AlarmStatus(char asChar){
        this.asChar=asChar;
    }

    public static AlarmStatus fromChar(char c) {
        for (AlarmStatus status : values()) {
            if (status.asChar == c) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status char: " + c);
    }
}
