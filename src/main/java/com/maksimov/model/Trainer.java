package com.maksimov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * Trainer- class Trainer
 */
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "trainer", uniqueConstraints = {@UniqueConstraint(columnNames = {"surname", "name", "patronymic"})})
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String surname;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String patronymic;

    @NonNull
    private int experience;

    @OneToOne(mappedBy = "trainer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TrainerSchedule trainerSchedule;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Training> trainings = new ArrayList<>();
}
