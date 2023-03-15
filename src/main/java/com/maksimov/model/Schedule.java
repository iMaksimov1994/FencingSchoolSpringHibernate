package com.maksimov.model;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author Konstantin Maksimov
 * Schedule- class Schedule
 */
@Data
public class Schedule {
    private String dayWeek;
    private LocalTime start;
    private LocalTime end;

    public Schedule(String dayWeek, LocalTime start, LocalTime end) {
        this.dayWeek = dayWeek;
        this.start = start;
        this.end = end;
    }
}
