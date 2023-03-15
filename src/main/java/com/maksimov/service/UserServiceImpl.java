package com.maksimov.service;

import com.maksimov.model.User;
import com.maksimov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Konstantin Maksimov
 * UserServiceImpl- class implements methods for registration, check registration, delete and deleting user
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    /**
     * setUserRepository- method realize Dependency Injection for UserRepository
     *
     * @param userRepository- object that implements an interface of UserRepository
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * registerUser- method for registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @param name-     name of user
     * @return object user
     */
    @Override
    public User registerUser(String login, String password, String name) {
        User user = new User(login, password, name);
        user.setRegDate(new Date());
        try {
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User already register");
        }
    }

    /**
     * checkRegUser- method to check registration user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return object user
     */
    @Override
    public User checkRegUser(String login, String password) {
        return this.userRepository.getUserByLoginAndPassword(login, password);
    }

    /**
     * deleteUser- method to delete user from database by id
     *
     * @param id- id user in database
     * @return object user
     */
    @Override
    public User deleteUser(long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.delete(user);
        }
        return user;
    }

    /**
     * getUserById- method to get user from database by id
     *
     * @param id- id user in database
     * @return object user
     */
    @Override
    public User getUserById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * deleteUser- method to delete user by params
     *
     * @param login-    login of user
     * @param password- password of user
     * @return object user
     */
    @Override
    public User deleteUser(String login, String password) {
        List<User> users = userRepository.deleteUserByLoginAndPassword(login, password);
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }
}
