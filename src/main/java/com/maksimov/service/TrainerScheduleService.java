package com.maksimov.service;

import com.maksimov.model.Schedule;
import com.maksimov.model.TrainerSchedule;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerScheduleService-interface realize methods for getting, updating and deleting an trainerSchedule
 */
public interface TrainerScheduleService {
    /**
     * getTrainerScheduleId- Method to get of trainerSchedule from database by id
     *
     * @param id- id of trainerSchedule in database
     * @return object trainerSchedule
     */
    TrainerSchedule getTrainerSchedule(long id);

    /**
     * getTrainerScheduleList- method to get a list of schedules from database by id of trainerSchedule
     *
     * @param id- id of trainerSchedule in database
     * @return list of schedules
     */
    List<Schedule> getTrainerScheduleList(long id);

    /**
     * updateSchedule- method to update of trainerSchedule by params
     *
     * @param id-      id of trainerSchedule in database
     * @param dayWeek- day week of trainerSchedule in database
     * @param start-   time to start work day
     * @param end-     time to end work day
     * @return object trainerSchedule
     */
    TrainerSchedule updateSchedule(long id, String dayWeek, String start, String end);

    /**
     * deleteScheduleByDay- method to delete of trainerSchedule by id trainer and day of the week by id trainer
     *
     * @param id-      id of trainer in database
     * @param dayWeek- day week of trainerSchedule in database
     * @return object trainerSchedule
     */
    TrainerSchedule deleteScheduleByDay(long id, String dayWeek);

    /**
     * getTimeStartEnd- method to get an array of time start and end trainer work from database by id trainer
     *
     * @param idTrainer- id of trainer in database
     * @return array of time start and end trainer work
     */
    LocalTime[] getTimeStartEnd(long idTrainer, String localDate);
}
