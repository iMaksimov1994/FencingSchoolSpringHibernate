package com.maksimov.service;

import com.maksimov.model.Apprentice;
import com.maksimov.repository.ApprenticeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * ApprenticeServiceImpl- class implements methods for adding, getting, updating and deleting an apprentice
 */
@Service
@Transactional
public class ApprenticeServiceImpl implements ApprenticeService {
    ApprenticeRepository apprenticeRepository;

    /**
     * ApprenticeServiceImpl- constructor realize Dependency Injection for ApprenticeRepository
     *
     * @param apprenticeRepository- object that implements an interface of ApprenticeRepository
     */
    public ApprenticeServiceImpl(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    /**
     * addApprentice- method to add an apprentice to database by params
     *
     * @param surname-     surname of apprentice
     * @param name-        name of apprentice
     * @param patronymic-  patronymic of apprentice
     * @param phoneNumber- phoneNumber of apprentice
     * @return object apprentice
     */
    @Override
    public Apprentice addApprentice(String surname, String name, String patronymic, String phoneNumber) {
        Apprentice apprentice = new Apprentice(surname, name, patronymic, phoneNumber);
        try {
            return this.apprenticeRepository.save(apprentice);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice already exist");
        }
    }

    /**
     * getApprentices- method to get a list of apprentices from database
     *
     * @return list apprentices
     */
    @Override
    public List<Apprentice> getApprentices() {
        return this.apprenticeRepository.findAll();
    }

    /**
     * getApprenticeById- method to get of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return object apprentice
     */
    @Override
    public Apprentice getApprenticeById(long id) {
        return this.apprenticeRepository.findById(id).orElse(null);
    }

    /**
     * updateApprentice- method to update of apprentice by params in database
     *
     * @param surname-     surname of apprentice
     * @param name-        name of apprentice
     * @param patronymic-  patronymic of apprentice
     * @param phoneNumber- phoneNumber of apprentice
     * @param id-          id of apprentice in database
     * @return object apprentice
     */
    @Override
    public Apprentice updateApprentice(String surname, String name, String patronymic, String phoneNumber, long id) {
        Apprentice apprentice = this.apprenticeRepository.findById(id).orElse(null);
        if (apprentice != null) {
            apprentice.setSurname(surname);
            apprentice.setName(name);
            apprentice.setPatronymic(patronymic);
            apprentice.setPhoneNumber(phoneNumber);
            try {
                return this.apprenticeRepository.save(apprentice);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * deleteApprentice- method to delete of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return object apprentice
     */
    @Override
    public Apprentice deleteApprentice(long id) {
        Apprentice apprentice = this.apprenticeRepository.findById(id).orElse(null);
        if (apprentice != null) {
            this.apprenticeRepository.delete(apprentice);
        }
        return apprentice;
    }
}
