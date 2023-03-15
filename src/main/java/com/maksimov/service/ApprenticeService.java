package com.maksimov.service;

import com.maksimov.model.Apprentice;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * ApprenticeService- interface realize methods for adding, getting, updating and deleting an apprentice
 */
public interface ApprenticeService {
    /**
     * addApprentice- method to add an apprentice to database by params
     *
     * @param surname-     surname of apprentice
     * @param name-        name of apprentice
     * @param patronymic-  patronymic of apprentice
     * @param phoneNumber- phoneNumber of apprentice
     * @return object apprentice
     */
    Apprentice addApprentice(String surname, String name, String patronymic, String phoneNumber);

    /**
     * getApprentices- method to get a list of apprentices from database
     *
     * @return list of object apprentices
     */
    List<Apprentice> getApprentices();

    /**
     * getApprenticeById- method to get of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return object apprentice
     */
    Apprentice getApprenticeById(long id);

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
    Apprentice updateApprentice(String surname, String name, String patronymic, String phoneNumber, long id);

    /**
     * deleteApprentice- method to delete of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return object apprentice
     */
    Apprentice deleteApprentice(long id);
}
