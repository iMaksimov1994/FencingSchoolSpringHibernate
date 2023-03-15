package com.maksimov.repository;

import com.maksimov.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Konstantin Maksimov
 * TrainerRepository- interface realize the interaction of the Trainer with the database
 */
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
