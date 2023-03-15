package com.maksimov.repository;

import com.maksimov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Konstantin Maksimov
 * UserRepository- interface realize the interaction of the UserRepository with the database
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * getUserByLoginAndPassword- method to get a user from database by login and password
     *
     * @param login-    login of user
     * @param password- password of user
     * @return object user
     */
    User getUserByLoginAndPassword(String login, String password);

    /**
     * deleteUserByLoginAndPassword- method to delete a user from database by login and password
     *
     * @param login-    login of user
     * @param password- password of user
     * @return list of users
     */
    List<User> deleteUserByLoginAndPassword(String login, String password);
}
