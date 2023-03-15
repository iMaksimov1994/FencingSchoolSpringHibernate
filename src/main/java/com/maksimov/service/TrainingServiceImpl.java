package com.maksimov.service;

import com.maksimov.model.Apprentice;
import com.maksimov.model.Trainer;
import com.maksimov.model.Training;
import com.maksimov.repository.ApprenticeRepository;
import com.maksimov.repository.TrainerRepository;
import com.maksimov.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * TrainingServiceImpl- class implements methods for adding, getting, updating and deleting a training
 */
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    TrainingRepository trainingRepository;
    TrainerRepository trainerRepository;
    ApprenticeRepository apprenticeRepository;

    /**
     * setTrainingRepository- method realize Dependency Injection for TrainingRepository
     *
     * @param trainingRepository- object that implements an interface of TrainingRepository
     */
    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
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
     * setApprenticeRepository- method realize Dependency Injection for ApprenticeRepository
     *
     * @param apprenticeRepository- object that implements an interface of ApprenticeRepository
     */
    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    /**
     * isOverlapping- method to get bool about overlapping training time between two trainings
     *
     * @param start1- time to start 1-st training
     * @param start2- time to start 2-nd training
     * @return true or false
     */
    private boolean isOverlapping(LocalTime start1, LocalTime start2) {
        LocalTime end1 = start1.plusMinutes(90);
        LocalTime end2 = start2.plusMinutes(90);
        return start1.getSecond() <= end2.getSecond() && start2.getSecond() <= end1.getSecond();
    }

    /**
     * scheduleOverlapping- method return number of overlapping trainings
     *
     * @param trainings- list of trainings
     * @param start-     time start training
     * @return number of overlapping trainings
     */
    private int scheduleOverlapping(List<Training> trainings, String start) {
        int count = 0;
        for (Training training : trainings) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            if (isOverlapping(training.getTimeStart(), LocalTime.parse(start, formatter))) {
                count++;
            }
        }
        return count;
    }

    /**
     * trainingIntersection- method return number of intersection trainings in the same gym and date
     *
     * @param trainingsByDateAndNumberGym- list of trainings
     * @param start1-                      time to start of training
     * @return return number of intersection trainings in the same gym and date
     */
    private int trainingIntersection(List<Training> trainingsByDateAndNumberGym, LocalTime start1) {
        LocalTime end1 = start1.plusMinutes(90);
        return (int) trainingsByDateAndNumberGym.stream().filter(training -> {
            LocalTime start2 = training.getTimeStart();
            return end1.getSecond() < start2.getSecond();
        }).count();
    }

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
    @Override
    public Training addTraining(long idApprentice, long idTrainer, int numberOfGym, String start, String date) {
        Trainer trainer = this.trainerRepository.findById(idTrainer).orElse(null);
        Apprentice apprentice = this.apprenticeRepository.findById(idApprentice).orElse(null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeStart = LocalTime.parse(start, formatter);
        if (apprentice != null) {
            if (this.trainingRepository.getTrainingsByApprenticeAndDate(apprentice, localDate).size() != 0) {
                throw new IllegalArgumentException("Apprentice cannot attend several training sessions " +
                        "at once on the same day!");
            }
        }
        if (trainer != null) {
            List<Training> trainings = trainer.getTrainings();
            if (trainings != null) {
                if (scheduleOverlapping(trainings, start) > 3) {
                    throw new IllegalArgumentException("Intersection with other apprentice!");
                }
            }
            if (trainingIntersection(this.trainingRepository.getTrainingsByDateAndNumberGym(LocalDate.parse(date,
                    dateTimeFormatter), numberOfGym), timeStart) > 10) {
                throw new IllegalArgumentException("No more than 10 people can work in the gym!");
            }
            if (apprentice != null) {
                Training training = new Training(numberOfGym, trainer, apprentice, localDate, timeStart);
                try {
                    return this.trainingRepository.save(training);
                } catch (DataIntegrityViolationException e) {
                    throw new IllegalArgumentException("This training already exist!");
                }
            }
        }
        return null;
    }

    /**
     * getTrainingById- method to get a training from database by id
     *
     * @param id- id training in database
     * @return object training
     */
    @Override
    public Training getTrainingById(long id) {
        return this.trainingRepository.findById(id).orElse(null);
    }

    /**
     * getTrainingsByIdTrainer- method to get list of the trainings from database by id trainer
     *
     * @param idT- id trainer in database
     * @return list of trainings
     */
    @Override
    public List<Training> getTrainingsByIdTrainer(long idT) {
        return this.trainingRepository.getTrainingsByTrainer_Id(idT);
    }

    /**
     * getAllTrainings- method to get list of trainings from database
     *
     * @return list of trainings
     */
    @Override
    public List<Training> getAllTrainings() {
        return this.trainingRepository.findAll();
    }

    /**
     * getTrainingByIdApprentice- method to get training from database by id apprentice
     *
     * @param id- id apprentice in database
     * @return list of trainings
     */
    @Override
    public List<Training> getTrainingsByIdApprentice(long id) {
        return this.trainingRepository.getTrainingsByApprentice_Id(id);
    }

    /**
     * deleteTrainingById- method to delete training from database by id
     *
     * @param id- id of training in database
     * @return object training
     */
    @Override
    public Training deleteTrainingById(long id) {
        Training training = this.trainingRepository.findById(id).orElse(null);
        if (training != null) {
            this.trainingRepository.delete(training);
            return training;
        }
        return null;
    }
}
