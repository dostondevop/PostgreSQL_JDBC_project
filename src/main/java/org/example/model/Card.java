package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private int id;
    private int owner_id;
    private String card_number;
    private String card_name;
    private String card_expiry_date;
    private int card_cvv;
    private String created_date;
    private String updated_date;
    private boolean active;

    public Card(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.owner_id = resultSet.getInt("owner_id");
        this.card_name = resultSet.getString("card_name");
        this.card_number = resultSet.getString("card_number");
        this.card_expiry_date = resultSet.getString("card_expiry_date");
//        this.card_cvv = resultSet.getInt("card_cvv");
        this.created_date = resultSet.getString("created_date");
        this.updated_date = resultSet.getString("updated_date");
        this.active = resultSet.getBoolean("active");
    }
}