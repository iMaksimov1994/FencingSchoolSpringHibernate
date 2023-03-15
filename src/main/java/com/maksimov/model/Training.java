package com.maksimov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Konstantin Maksimov
 * Training- class Training
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private int numberGym;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Trainer trainer;
    @ManyToOne()
    @NonNull
    @JoinColumn(name = "apprentice_id", nullable = false)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Apprentice apprentice;

    @NonNull
    @Column(nullable = false)
    private LocalDate date;

    @NonNull
    @Column(nullable = false)
    private LocalTime timeStart;
}
