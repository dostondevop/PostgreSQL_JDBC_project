package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.example.service.Utilities.UserValidation;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User registerUser(User user) {
        if(!UserValidation.isValidEmail(user.getEmail())) {
            System.out.println("Wrong email...!");
            return null;
        } else if (!UserValidation.isValidPassword(user.getPassword())) {
            System.out.println("Invalid password...!");
            return null;
        } else if (userDao.signIn(user.getUsername(), user.getPassword())) {
            System.out.println("User already exists...!");
            return null;
        }

        return userDao.addUser(user);
        // validation
            /*
            * 1. password length - min 8
            * 2. password (A,a,1,^)
            * 3. email -> (@gmail.com, @mail.ru)
            * */
        // call to UserDao
    }

    public boolean signIn(String username, String password) {
        return userDao.signIn(username, password);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }
}