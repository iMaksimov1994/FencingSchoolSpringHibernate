package com.maksimov.service;

import com.maksimov.model.Trainer;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerService- interface realize methods for adding, getting, updating and deleting a trainer
 */
public interface TrainerService {
    /**
     * addTrainer- method to add a trainer to database by params
     *
     * @param surname-    surname of trainer
     * @param name-       name of trainer
     * @param patronymic- patronymic of trainer
     * @param experience- experience of trainer
     * @return object trainer
     */
    Trainer addTrainer(String surname, String name, String patronymic, int experience);

    /**
     * getTrainers- method to get a list of trainers from database
     *
     * @return list of trainers
     */
    List<Trainer> getTrainers();

    /**
     * getTrainerById- method to get of trainer from database by id
     *
     * @param id- id of trainer in database
     * @return object trainer
     */
    Trainer getTrainerById(long id);

    /**
     * updateTrainer- method to update of trainer by params
     *
     * @param surname-    surname of trainer
     * @param name-       name of trainer
     * @param patronymic- patronymic of trainer
     * @param experience- experience of trainer
     * @param id-         id of trainer in database
     * @return object trainer
     */
    Trainer updateTrainer(String surname, String name, String patronymic, int experience, long id);

    /**
     * deleteTrainer- method to delete of trainer from database by id
     *
     * @param id- id of trainer in database
     * @return object trainer
     */
    Trainer deleteTrainer(long id);
}
