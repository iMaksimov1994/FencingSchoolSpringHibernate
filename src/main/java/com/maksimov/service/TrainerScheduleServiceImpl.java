package com.maksimov.service;

import com.maksimov.model.Schedule;
import com.maksimov.model.Trainer;
import com.maksimov.model.TrainerSchedule;
import com.maksimov.repository.TrainerRepository;
import com.maksimov.repository.TrainerScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainerScheduleServiceImpl- class implements methods for adding, getting, updating and deleting an apprentice
 */
@Service
@Transactional
public class TrainerScheduleServiceImpl implements TrainerScheduleService {
    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerRepository trainerRepository;

    /**
     * setTrainerScheduleRepository- method realize Dependency Injection for TrainerScheduleRepository
     *
     * @param trainerScheduleRepository- object that implements an interface of TrainerScheduleRepository
     */
    @Autowired
    public void setTrainerScheduleRepository(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

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
     * getTrainerScheduleId- method to get of trainerSchedule from database by id
     *
     * @param id- id of trainerSchedule in database
     * @return object trainerSchedule
     */
    @Override
    public TrainerSchedule getTrainerSchedule(long id) {
        return this.trainerScheduleRepository.findById(id).orElse(null);
    }

    /**
     * getTrainerScheduleList- method to get a list of schedules from database
     *
     * @param id- id of trainerSchedule in database
     * @return list of schedules
     */
    @Override
    public List<Schedule> getTrainerScheduleList(long id) {
        TrainerSchedule trainerSchedule = this.trainerScheduleRepository.findById(id).orElse(null);
        if (trainerSchedule != null) {
            List<Schedule> schedules = trainerSchedule.scheduleList();
            if (schedules != null) {
                return schedules;
            }
        }
        return new ArrayList<>();
    }

    /**
     * updateSchedule- method to update of trainerSchedule by params
     *
     * @param id-      id of trainerSchedule in database
     * @param dayWeek- day week of trainerSchedule in database
     * @param start-   time to start work day
     * @param end-     time to end work day
     * @return object trainerSchedule
     */
    @Override
    public TrainerSchedule updateSchedule(long id, String dayWeek, String start, String end) {
        Trainer trainer = this.trainerRepository.findById(id).orElse(null);
        if (trainer != null) {
            TrainerSchedule trainerSchedule = trainer.getTrainerSchedule();
            if (trainerSchedule == null) {
                trainerSchedule = new TrainerSchedule(trainer);
            }
            try {
                trainerSchedule.addSchedule(dayWeek, start, end);
                return this.trainerScheduleRepository.save(trainerSchedule);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                throw new IllegalArgumentException("Unable to add schedule to this trainer in method updateSchedule");
            }
        }
        return null;
    }

    /**
     * deleteScheduleByDay- method to delete of trainerSchedule by id of trainer and day
     *
     * @param id-      id of trainer in database
     * @param dayWeek- day of the week of trainerSchedule in database
     * @return object trainerSchedule
     */
    @Override
    public TrainerSchedule deleteScheduleByDay(long id, String dayWeek) {
        Trainer trainer = this.trainerRepository.findById(id).orElse(null);
        if (trainer != null) {
            TrainerSchedule trainerSchedule = trainer.getTrainerSchedule();
            if (trainerSchedule == null) {
                trainerSchedule = new TrainerSchedule(trainer);
            }
            try {
                trainerSchedule.deleteSchedule(dayWeek);
                return this.trainerScheduleRepository.save(trainerSchedule);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                throw new IllegalArgumentException("Unable to update trainerSchedule in method deleteScheduleByDay");
            }
        }
        return null;
    }

    /**
     * getTimeStartEnd- method to get an array of time start and end trainer work from database by id trainer
     *
     * @param idTrainer- id of trainer in database
     * @return array of time start and end trainer work
     */
    @Override
    public LocalTime[] getTimeStartEnd(long idTrainer, String localDate) {
        Trainer trainer = this.trainerRepository.findById(idTrainer).orElse(null);
        if (trainer != null) {
            TrainerSchedule trainerSchedule = trainer.getTrainerSchedule();
            if (trainerSchedule != null) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalTime[] localTimes = trainerSchedule.startEnd(LocalDate.parse(localDate, dateFormatter));
                if (localTimes.length != 0) {
                    return localTimes;
                } else {
                    return new LocalTime[0];
                }
            }
        }
        return new LocalTime[0];
    }
}
