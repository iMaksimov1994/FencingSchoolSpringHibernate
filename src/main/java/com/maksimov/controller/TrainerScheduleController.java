package com.maksimov.controller;

import com.maksimov.model.TrainerSchedule;
import com.maksimov.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

/**
 * @author Konstantin Maksimov
 * TrainerScheduleController- class realize methods for adding, getting, updating and deleting trainerSchedule
 */
@RestController
@RequestMapping("/trainerSchedule")
public class TrainerScheduleController {
    private TrainerScheduleService trainerScheduleService;

    /**
     * setTrainerScheduleService- method realize Dependency Injection for TrainerScheduleService
     *
     * @param trainerScheduleService- object that implements an interface of TrainerScheduleService
     */
    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService) {
        this.trainerScheduleService = trainerScheduleService;
    }

    /**
     * getTrainerScheduleId- method to get of trainerSchedule from database by id
     *
     * @param id- id of trainerSchedule in database
     * @return JSON of trainerSchedule or Exception message
     */
    @GetMapping("/id/{id}")
    public ResponseEntity getTrainerScheduleId(@PathVariable long id) {
        TrainerSchedule trainerSchedule = this.trainerScheduleService.getTrainerSchedule(id);
        if (trainerSchedule != null) {
            return new ResponseEntity<>(trainerSchedule, HttpStatus.OK);
        }
        return new ResponseEntity("No trainerSchedule with this id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getTrainerScheduleListId- method to get a list of schedules from database by id trainerSchedule
     *
     * @param id- id of trainerSchedule in database
     * @return JSON of list of schedules or Exception message
     */
    @GetMapping("/list/{id}")
    public ResponseEntity getTrainerScheduleListId(@PathVariable long id) {
        return new ResponseEntity(this.trainerScheduleService.getTrainerScheduleList(id), HttpStatus.OK);
    }

    /**
     * getTimeStartEnd- method to get an array of time start and end trainer work from database by id trainer and date
     *
     * @param idT-       id of trainer in database
     * @param localDate- date
     * @return JSON an array of localTimes or Exception message
     */
    @GetMapping("/idT/{idT}/localDate/{localDate}")
    public ResponseEntity getTimeStartEnd(@PathVariable long idT, @PathVariable String localDate) {
        LocalTime[] timeStartEnd = this.trainerScheduleService.getTimeStartEnd(idT, localDate);
        if (timeStartEnd.length != 0) {
            return new ResponseEntity(timeStartEnd, HttpStatus.OK);
        } else {
            return new ResponseEntity("Trainer with this id does not exist or trainer does not trainer schedule!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * updateSchedule- method to update of trainerSchedule by params
     *
     * @param id-      id of trainerSchedule in database
     * @param dayWeek- day week of trainerSchedule in database
     * @param start-   time to start work day
     * @param end-     time to end work day
     * @return JSON of trainerSchedule or Exception message
     */
    @PostMapping("/{id}")
    public ResponseEntity updateSchedule(@PathVariable long id, @RequestParam String dayWeek,
                                         @RequestParam String start, @RequestParam String end) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.updateSchedule(id, dayWeek, start, end);
            if (trainerSchedule != null) {
                return new ResponseEntity(trainerSchedule, HttpStatus.OK);
            } else {
                return new ResponseEntity("There is no trainer with this id!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * deleteScheduleByDay- method to delete of trainerSchedule by id of trainer and day
     *
     * @param id-      id of trainer in database
     * @param dayWeek- day of the week of trainerSchedule in database
     * @return JSON of trainerSchedule or Exception message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteScheduleByDay(@PathVariable long id, @RequestParam String dayWeek) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.deleteScheduleByDay(id, dayWeek);
            if (trainerSchedule != null) {
                return new ResponseEntity(trainerSchedule, HttpStatus.OK);
            } else {
                return new ResponseEntity("There is no trainer with this id!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
