package com.maksimov.controller;

import com.maksimov.model.Training;
import com.maksimov.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantin Maksimov
 * TrainingController- class realize methods for adding, getting, updating and deleting training
 */
@RestController
@RequestMapping("/training")
public class TrainingController {
    TrainingService trainingService;

    /**
     * setTrainingService- method realize Dependency Injection for TrainingService
     *
     * @param trainingService- object that implements an interface of TrainingService
     */
    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /**
     * addTraining- method to add training to database by params
     *
     * @param idApprentice- id of apprentice in database
     * @param idTrainer-    id of trainer in database
     * @param numberOfGym-  number of gym
     * @param start-        training start time
     * @param date-         date of training
     * @return JSON of training or Exception message
     */
    @PostMapping("/idApprentice/{idApprentice}/idTrainer/{idTrainer}/numberOfGym/{numberOfGym}/start/{start}/date/{date}")
    public ResponseEntity addTraining(@PathVariable long idApprentice, @PathVariable long idTrainer,
                                      @PathVariable int numberOfGym, @PathVariable String start, @PathVariable String date) {
        try {
            Training training = trainingService.addTraining(idApprentice, idTrainer, numberOfGym, start, date);
            return new ResponseEntity(Objects.requireNonNullElse(training, "Trainer or Apprentice does" +
                    " not exist!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * getListOfGym- method to get list of the number of the gym from database
     *
     * @return JSON of list of the number of the gym or Exception message
     */
    @GetMapping("/listOfGym")
    public ResponseEntity<ArrayList<Integer>> getListOfGym() {
        ArrayList<Integer> listOfGym = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listOfGym.add(i);
        }
        return new ResponseEntity<>(listOfGym, HttpStatus.OK);
    }

    /**
     * getTrainingById- method to get a training from database by id
     *
     * @param id- id training in database
     * @return JSON of training or Exception message
     */
    @GetMapping("/id/{id}")
    public ResponseEntity getTrainingById(@PathVariable long id) {
        Training trainingById = this.trainingService.getTrainingById(id);
        if (trainingById != null) {
            return new ResponseEntity(trainingById, HttpStatus.OK);
        }
        return new ResponseEntity("There is no training with this id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getTrainingsByIdTrainer- method to get list of the trainings from database by id trainer
     *
     * @param idT- id trainer in database
     * @return JSON of list trainings or Exception message
     */
    @GetMapping("/{idT}")
    public ResponseEntity getTrainingsByIdTrainer(@PathVariable long idT) {
        List<Training> trainingsByIdTrainer = this.trainingService.getTrainingsByIdTrainer(idT);
        if (trainingsByIdTrainer != null) {
            return new ResponseEntity(trainingsByIdTrainer, HttpStatus.OK);
        }
        return new ResponseEntity("There is no trainings with this trainer id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getTrainingByIdTrainer- method to get training from database by id trainer
     *
     * @param idT- id trainer in database
     * @return JSON of training or Exception message
     */
    @GetMapping("/idT/{idT}")
    public ResponseEntity getTrainingByIdTrainer(@PathVariable long idT) {
        Training trainingById = this.trainingService.getTrainingById(idT);
        if (trainingById != null) {
            return new ResponseEntity(trainingById, HttpStatus.OK);
        }
        return new ResponseEntity("There is no training with this trainer id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getTrainingByIdApprentice- method to get training from database by id apprentice
     *
     * @param idA- id apprentice in database
     * @return JSON of training or Exception message
     */
    @GetMapping("/idA/{idA}")
    public ResponseEntity getTrainingByIdApprentice(@PathVariable long idA) {
        Training trainingById = this.trainingService.getTrainingById(idA);
        if (trainingById != null) {
            return new ResponseEntity(trainingById, HttpStatus.OK);
        }
        return new ResponseEntity("There is no training with this apprentice id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getAllTrainings- method to get list of trainings from database
     *
     * @return JSON of list trainings or Exception message
     */
    @GetMapping()
    public ResponseEntity getAllTrainings() {
        List<Training> allTrainings = this.trainingService.getAllTrainings();
        if (allTrainings != null) {
            return new ResponseEntity(allTrainings, HttpStatus.OK);
        }
        return new ResponseEntity("There is no training with this trainer id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * deleteTrainingById- method to delete training from database by id
     *
     * @param id- id of training in database
     * @return JSON of training or Exception message
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteTrainingById(@PathVariable long id) {
        Training training = this.trainingService.deleteTrainingById(id);
        if (training != null) {
            return new ResponseEntity(training, HttpStatus.OK);
        }
        return new ResponseEntity("Training with this id does not exist!", HttpStatus.BAD_REQUEST);
    }
}
