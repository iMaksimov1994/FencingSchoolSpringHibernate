package com.maksimov.controller;

import com.maksimov.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maksimov.service.UserService;

/**
 * @author Konstantin Maksimov
 * UserController- class realize methods for registration, check registration, delete and deleting trainerSchedule
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    /**
     * setUserService- method realize Dependency Injection for UserService
     *
     * @param userService- object that implements an interface of UserService
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * regUser- method for registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @param name-     name of user
     * @return JSON of user or Exception message
     */
    @PostMapping("/login/{login}/password/{password}/name/{name}")
    public ResponseEntity regUser(@PathVariable String login, @PathVariable String password, @PathVariable String name) {
        try {
            return new ResponseEntity(userService.registerUser(login, password, name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * checkRegUser- method to check registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return JSON of user or Exception message
     */
    @PostMapping("/login/{login}/password/{password}")
    public ResponseEntity checkRegUser(@PathVariable String login, @PathVariable String password) {
        User user = this.userService.checkRegUser(login, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Authorization error!", HttpStatus.BAD_REQUEST);
    }

    /**
     * deleteUser- method to delete user from database by id
     *
     * @param id- id user in database
     * @return JSON of user or Exception message
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        User user = this.userService.deleteUser(id);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity("Delete error!", HttpStatus.BAD_REQUEST);
    }

    /**
     * deleteUser- method to delete user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return JSON of user or Exception message
     */
    @DeleteMapping("/login/{login}/password/{password}")
    public ResponseEntity deleteUser(@PathVariable String login, @PathVariable String password) {
        User user = this.userService.deleteUser(login, password);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity("Delete error!", HttpStatus.BAD_REQUEST);
    }

    /**
     * getUserById- method to get user by id from database
     *
     * @param id- id user in database
     * @return JSON of user or Exception message
     */
    @GetMapping("/id/{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        User userById = this.userService.getUserById(id);
        if (userById != null) {
            return new ResponseEntity(userById, HttpStatus.OK);
        }
        return new ResponseEntity("User with this id does not exist!", HttpStatus.BAD_REQUEST);
    }
}
