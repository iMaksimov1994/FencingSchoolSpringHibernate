package com.maksimov.controller;

import com.maksimov.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maksimov.service.TrainerService;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerController- class realize methods for adding, getting, updating and deleting trainer
 */
@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerService trainerService;

    /**
     * setTrainerService- method realize Dependency Injection for TrainerService
     *
     * @param trainerService- object that implements an interface of TrainerService
     */
    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * addTrainer- method to add trainer to database by params
     *
     * @param surname-    surname of trainer
     * @param name-       name of trainer
     * @param patronymic- patronymic of trainer
     * @param experience- experience of trainer
     * @return JSON of trainer or Exception message
     */
    @PostMapping("/surname/{surname}/name/{name}/patronymic/{patronymic}/experience/{experience}")
    public ResponseEntity addTrainer(@PathVariable String surname, @PathVariable String name,
                                     @PathVariable String patronymic, @PathVariable int experience) {
        try {
            return new ResponseEntity(trainerService.addTrainer(surname, name, patronymic, experience), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * getTrainers- method to get a list of trainers from database
     *
     * @return JSON of list of trainers or Exception message
     */
    @GetMapping()
    public ResponseEntity getTrainers() {
        List<Trainer> trainers = this.trainerService.getTrainers();
        if (trainers != null) {
            return new ResponseEntity(trainers, HttpStatus.OK);
        }
        return new ResponseEntity("There is no trainers!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getTrainerById- method to get a trainer from database by id
     *
     * @param id- id of trainer in database
     * @return JSON of trainer or Exception message
     */
    @GetMapping("/id/{id}")
    public ResponseEntity getTrainerById(@PathVariable long id) {
        Trainer trainerById = this.trainerService.getTrainerById(id);
        if (trainerById != null) {
            return new ResponseEntity(trainerById, HttpStatus.OK);
        }
        return new ResponseEntity("There is no trainer with this id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * updateTrainer- method to update a trainer by params
     *
     * @param surname-    surname of trainer
     * @param name-       name of trainer
     * @param patronymic- patronymic of trainer
     * @param experience- experience of trainer
     * @param id-         id of trainer in database
     * @return JSON of trainer or Exception message
     */
    @PutMapping("/surname/{surname}/name/{name}/patronymic/{patronymic}/experience/{experience}/id/{id}")
    public ResponseEntity updateTrainer(@PathVariable String surname, @PathVariable String name, @PathVariable
    String patronymic, @PathVariable int experience, @PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.updateTrainer(surname, name, patronymic, experience, id);
            if (trainer != null) {
                return new ResponseEntity(trainer, HttpStatus.OK);
            }
            return new ResponseEntity("There is no trainer with this id!", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * deleteTrainer- method to delete a trainer from database by id
     *
     * @param id- id of trainer in database
     * @return JSON of trainer or Exception message
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteTrainer(@PathVariable long id) {
        Trainer trainer = this.trainerService.deleteTrainer(id);
        if (trainer != null) {
            return new ResponseEntity(trainer, HttpStatus.OK);
        }
        return new ResponseEntity("Trainer with this id does not exist!", HttpStatus.BAD_REQUEST);
    }
}
