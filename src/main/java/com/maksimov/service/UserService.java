package com.maksimov.service;

import com.maksimov.model.User;

/**
 * @author Konstantin Maksimov
 * UserService- interface realize methods for registration, check registration, delete and deleting user
 */
public interface UserService {
    /**
     * checkRegUser- method to check registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return object user
     */
    User checkRegUser(String login, String password);

    /**
     * registerUser- method for registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @param name-     name of user
     * @return object user
     */
    User registerUser(String login, String password, String name);

    /**
     * getUserById- method to get user by id from database
     *
     * @param id- id user in database
     * @return object user
     */
    User getUserById(long id);

    /**
     * deleteUser- method to delete user from database by id
     *
     * @param id- id user in database
     * @return object user
     */
    User deleteUser(long id);

    /**
     * deleteUser- method to check registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return object user
     */
    User deleteUser(String login, String password);
}
