package org.example.dao;

import org.example.dao.config.DatabaseConfig;
import org.example.dao.config.PostgresDatabaseConfig;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final DatabaseConfig databaseConfig;
    private static final String GET_USER_LIST = "select * from getUsers()";
    private static final String INSERT_USER = "select * from adduser(p_name := ?, p_username := ?, p_password := ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM getuserbyid(?);";
    private static final String SIGN_IN = "select * from signin(p_username := ?, p_password := ?)";

    public UserDao() {
        this.databaseConfig = new PostgresDatabaseConfig();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connect = databaseConfig.connect()) {

            PreparedStatement statement = connect.prepareStatement(GET_USER_LIST);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet);
                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User addUser(User user) {
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(INSERT_USER)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet);
            }else {
                throw new IllegalArgumentException();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int userId) {
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(GET_USER_BY_ID)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet);
            } else {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching user by ID.", e);
        }
    }


    public boolean signIn(String username, String password) {
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(SIGN_IN)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}