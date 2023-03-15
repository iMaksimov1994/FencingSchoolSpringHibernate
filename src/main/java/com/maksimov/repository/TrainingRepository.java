package com.maksimov.repository;

import com.maksimov.model.Apprentice;
import com.maksimov.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainingRepository- interface realize the interaction of the TrainingRepository with the database
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * getTrainingsByDateAndNumberGym- method to get list of trainings from database by date and number of the gym
     *
     * @param date-      date
     * @param numberGym- numberGym
     * @return list of trainings
     */
    List<Training> getTrainingsByDateAndNumberGym(LocalDate date, int numberGym);

    /**
     * getTrainingsByTrainer_Id- method to get list of trainings from database by date and id trainer
     *
     * @param id- id of trainer
     * @return list of trainings
     */
    List<Training> getTrainingsByTrainer_Id(long id);

    /**
     * getTrainingsByApprentice_Id- method to get list of trainings from database by id apprentice
     *
     * @param id- id of apprentice
     * @return list of trainings
     */
    List<Training> getTrainingsByApprentice_Id(long id);

    /**
     * getTrainingsByApprenticeAndDate- method to get list of trainings from database by apprentice and date
     *
     * @param apprentice- object apprentice
     * @param localDate-  date
     * @return list of trainings
     */
    List<Training> getTrainingsByApprenticeAndDate(Apprentice apprentice, LocalDate localDate);
}
