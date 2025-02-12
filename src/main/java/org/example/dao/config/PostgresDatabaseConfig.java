package org.example.dao.config;

import org.example.dao.config.annotation.Configuration;
import org.example.dao.config.exception.PostgresConnectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PostgresDatabaseConfig implements DatabaseConfig {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "#####";
    private static final String DATABASE = "postgres";

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostgresConnectException("Postgres Database Connection Failed");
        }
    }
}
