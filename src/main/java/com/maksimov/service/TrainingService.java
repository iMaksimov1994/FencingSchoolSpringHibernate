package com.maksimov.service;

import com.maksimov.model.Training;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainingService- interface realize methods for adding, getting, updating and deleting a training
 */
public interface TrainingService {
    /**
     * addTraining- method to add training to database by params
     *
     * @param idApprentice- id of apprentice in database
     * @param idTrainer-    id of trainer in database
     * @param numberOfGym-  number of gym
     * @param start-        training start time
     * @param date-         date of training
     * @return object training
     */
    Training addTraining(long idApprentice, long idTrainer, int numberOfGym, String start, String date);

    /**
     * getTrainingById- method to get a training from database by id
     *
     * @param id- id training from database
     * @return object training
     */
    Training getTrainingById(long id);

    /**
     * getTrainingsByIdTrainer- method to get list of the trainings from database by id trainer
     *
     * @param idT- id trainer from database
     * @return list of the trainings
     */
    List<Training> getTrainingsByIdTrainer(long idT);

    /**
     * deleteTrainingById- method to delete training from database by id
     *
     * @param id- id of training from database
     * @return object training
     */
    Training deleteTrainingById(long id);

    /**
     * getAllTrainings- method to get list of trainings from database
     *
     * @return list of trainings
     */
    List<Training> getAllTrainings();

    /**
     * getTrainingByIdApprentice- method to get training from database by id apprentice
     *
     * @param id- id apprentice from database
     * @return list of trainings
     */
    List<Training> getTrainingsByIdApprentice(long id);
}
