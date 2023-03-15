package com.maksimov.controller;

import com.maksimov.model.Apprentice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maksimov.service.ApprenticeService;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * ApprenticeController- сlass realize methods for adding, getting, updating and deleting an apprentice
 */
@RestController
@RequestMapping("/apprentice")
public class ApprenticeController {
    private ApprenticeService apprenticeService;

    /**
     * setApprenticeService- method realize Dependency Injection for ApprenticeService
     *
     * @param apprenticeService- object that implements an interface of ApprenticeService
     */
    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    /**
     * addApprentice- method to add an apprentice to database by params
     *
     * @param surname-     surname of apprentice
     * @param name-        name of apprentice
     * @param patronymic-  patronymic of apprentice
     * @param phoneNumber- phoneNumber of apprentice
     * @return JSON of apprentice or Exception message
     */
    @PostMapping("/surname/{surname}/name/{name}/patronymic/{patronymic}/phoneNumber/{phoneNumber}")
    public ResponseEntity addApprentice(@PathVariable String surname, @PathVariable String name,
                                        @PathVariable String patronymic, @PathVariable String phoneNumber) {
        try {
            return new ResponseEntity(apprenticeService.addApprentice(surname, name, patronymic, phoneNumber), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * getApprentices- method to get a list of apprentices from database
     *
     * @return JSON of list of apprentices or Exception message
     */
    @GetMapping()
    public ResponseEntity getApprentices() {
        List<Apprentice> apprentices = this.apprenticeService.getApprentices();
        if (apprentices != null) {
            return new ResponseEntity(apprentices, HttpStatus.OK);
        }
        return new ResponseEntity("There is no apprentices!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getApprenticeById- method to get of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return JSON of apprentice or Exception message
     */
    @GetMapping("/id/{id}")
    public ResponseEntity getApprenticeById(@PathVariable long id) {
        Apprentice apprenticeById = this.apprenticeService.getApprenticeById(id);
        if (apprenticeById != null) {
            return new ResponseEntity(apprenticeById, HttpStatus.OK);
        }
        return new ResponseEntity("There is no apprentice with this user id!", HttpStatus.BAD_REQUEST);
    }

    /**
     * updateApprentice- method to update of apprentice by params in database
     *
     * @param surname-     surname of apprentice
     * @param name-        name of apprentice
     * @param patronymic-  patronymic of apprentice
     * @param phoneNumber- phoneNumber of apprentice
     * @param id-          id of apprentice in database
     * @return JSON of apprentice or Exception message
     */
    @PutMapping("/surname/{surname}/name/{name}/patronymic/{patronymic}/phoneNumber/{phoneNumber}/id/{id}")
    public ResponseEntity updateApprentice(@PathVariable String surname, @PathVariable String name, @PathVariable
    String patronymic, @PathVariable String phoneNumber, @PathVariable long id) {
        try {
            Apprentice apprentice = this.apprenticeService.updateApprentice(surname, name, patronymic, phoneNumber, id);
            if (apprentice != null) {
                return new ResponseEntity(apprentice, HttpStatus.OK);
            }
            return new ResponseEntity("There is no apprentice with this id!", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * deleteApprentice- method to delete of apprentice from database by id
     *
     * @param id- id of apprentice in database
     * @return JSON of apprentice or Exception message
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteApprentice(@PathVariable long id) {
        Apprentice apprentice = this.apprenticeService.deleteApprentice(id);
        if (apprentice != null) {
            return new ResponseEntity(apprentice, HttpStatus.OK);
        }
        return new ResponseEntity("Apprentice with this id does not exist!", HttpStatus.BAD_REQUEST);
    }
}
