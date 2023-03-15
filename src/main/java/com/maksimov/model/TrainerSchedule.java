package com.maksimov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerSchedule- class TrainerSchedule
 */
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class TrainerSchedule {
    @Id
    @Column(name = "trainer_id")
    private long id;
    @OneToOne
    @NonNull
    @MapsId
    @JoinColumn(name = "trainer_id")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // на чтение толко
    private Trainer trainer;

    @NonNull
    private LocalTime mondayStart;

    @NonNull
    private LocalTime mondayEnd;

    @NonNull
    private LocalTime tuesdayStart;

    @NonNull
    private LocalTime tuesdayEnd;

    @NonNull
    private LocalTime wednesdayStart;

    @NonNull
    private LocalTime wednesdayEnd;

    @NonNull
    private LocalTime thursdayStart;

    @NonNull
    private LocalTime thursdayEnd;

    @NonNull
    private LocalTime fridayStart;

    @NonNull
    private LocalTime fridayEnd;

    @NonNull
    private LocalTime saturdayStart;

    @NonNull
    private LocalTime saturdayEnd;

    @NonNull
    private LocalTime sundayStart;

    @NonNull
    private LocalTime sundayEnd;

    public TrainerSchedule(@NonNull Trainer trainer) {
        this.trainer = trainer;
    }

    public void addSchedule(String dayWeek, String start, String end) {
        dayWeek = dayWeek.toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            this.getClass().getDeclaredField(dayWeek + "Start").set(this, LocalTime.parse(start, formatter));
            this.getClass().getDeclaredField(dayWeek + "End").set(this, LocalTime.parse(end, formatter));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
    }

    public void deleteSchedule(String dayWeek) {
        try {
            this.getClass().getDeclaredField(dayWeek + "Start").set(this, null);
            this.getClass().getDeclaredField(dayWeek + "End").set(this, null);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
    }

    public List<Schedule> scheduleList() {
        String[] days = {"monday", "tuesday", "wednesday",
                "thursday", "friday", "saturday", "sunday"};
        List<Schedule> schedules = new ArrayList<>();
        for (String dayWeek : days) {
            try {
                LocalTime start = (LocalTime) this.getClass().getDeclaredField(dayWeek + "Start").get(this);
                LocalTime end = (LocalTime) this.getClass().getDeclaredField(dayWeek + "End").get(this);
                Schedule schedule = new Schedule(dayWeek, start, end);
                schedules.add(schedule);
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
            }
        }
        return schedules;
    }

    public LocalTime[] startEnd(LocalDate localDate) {
        String dayWeek = localDate.getDayOfWeek().toString().toLowerCase();
        try {
            LocalTime localTimeStart = (LocalTime) this.getClass().getDeclaredField(dayWeek + "Start").get(this);
            LocalTime localTimeEnd = (LocalTime) this.getClass().getDeclaredField(dayWeek + "End").get(this);
            return new LocalTime[]{localTimeStart, localTimeEnd};
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
        return new LocalTime[0];
    }
}
