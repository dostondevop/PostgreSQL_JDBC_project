package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String createdDate;
    private String updatedDate;
    private boolean active;

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("r_id");
        this.name = resultSet.getString("r_name");
        this.username = resultSet.getString("r_username");
//        this.password = resultSet.getString("r_password");
//        this.email = resultSet.getString("r_email");
        this.createdDate = resultSet.getString("r_created_date");
        this.updatedDate = resultSet.getString("r_updated_date");
        this.active = resultSet.getBoolean("r_active");
    }
}