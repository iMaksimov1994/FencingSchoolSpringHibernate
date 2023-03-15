package com.maksimov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * Apprentice- class Apprentice
 */
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Apprentice {
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
    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "apprentice", cascade = CascadeType.ALL)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Training> trainings;
}
