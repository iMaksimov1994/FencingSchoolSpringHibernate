package com.maksimov.repository;

import com.maksimov.model.TrainerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Konstantin Maksimov
 * TrainerScheduleRepository- interface realize the interaction of the TrainerSchedule with the database
 */
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long> {
}
