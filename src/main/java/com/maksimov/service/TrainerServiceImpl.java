package com.maksimov.service;

import com.maksimov.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import com.maksimov.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerServiceImpl- class implements methods for adding, getting, updating and deleting a trainer
 */
@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    /**
     * setTrainerRepository- method realize Dependency Injection for TrainerRepository
     *
     * @param trainerRepository- object that implements an interface of TrainerRepository
     */
    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    /**
     * addTrainer- method to add a trainer to database by params
     *
     * @param surname-    surname of trainer
     * @param name-       name of trainer
     * @param patronymic- patronymic of trainer
     * @param experience- experience of trainer
     * @return object trainer
     */
    @Override
    public Trainer addTrainer(String surname, String name, String patronymic, int experience) {
        Trainer trainer = new Trainer(surname, name, patronymic, experience);
        try {
            return trainerRepository.save(trainer);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Trainer already added");
        }
    }

    /**
     * getTrainers- method to get a list of trainers from database
     *
     * @return list of trainers
     */
    @Override
    public List<Trainer> getTrainers() {
        return this.trainerRepository.findAll();
    }

    /**
     * getTrainerById- method to get of trainer from database by id
     *
     * @param id- id of trainer in database
     * @return object trainer
     */
    @Override
    public Trainer getTrainerById(long id) {
        return this.trainerRepository.findById(id).orElse(null);
    }

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
    @Override
    public Trainer updateTrainer(String surname, String name, String patronymic, int experience, long id) {
        Trainer trainer = this.trainerRepository.findById(id).orElse(null);
        if (trainer != null) {
            trainer.setSurname(surname);
            trainer.setName(name);
            trainer.setPatronymic(patronymic);
            trainer.setExperience(experience);
            try {
                return this.trainerRepository.save(trainer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * deleteTrainer- method to delete of trainer from database by id
     *
     * @param id- id of trainer in database
     * @return object trainer
     */
    @Override
    public Trainer deleteTrainer(long id) {
        Trainer trainer = this.trainerRepository.findById(id).orElse(null);
        if (trainer != null) {
            this.trainerRepository.delete(trainer);
        }
        return trainer;
    }
}
