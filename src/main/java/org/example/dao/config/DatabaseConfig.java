package org.example.dao.config;

import java.sql.Connection;

public interface DatabaseConfig {
    Connection connect();
}
