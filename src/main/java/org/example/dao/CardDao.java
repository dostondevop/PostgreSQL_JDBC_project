package org.example.dao;

import org.example.dao.config.DatabaseConfig;
import org.example.dao.config.PostgresDatabaseConfig;
import org.example.model.Card;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao {
    private final DatabaseConfig databaseConfig;
    private static final String GET_CARDS_LIST = "select * from card";
    private static final String INSERT_CARD = "select * from addCard(p_owner_id := ?, p_card_number := ?, p_card_name := ?, p_card_expiry_date := ?, p_card_cvv := ?)";
    private static final String GET_CARDS_BY_OWNER_ID = "select * from getcards(p_owner_id :=?)";
    private static final String GET_CARD_BY_ID = "select * from getCardById(p_id := ?)";
    private static final String DELETE_CARD_BY_ID = "select * from deleteCard(p_card_id := ?)";
    private static final String IS_CARD_EXISTING = "select * from iscardexisting(p_card_number := ?)";

    public CardDao() {
        this.databaseConfig = new PostgresDatabaseConfig();
    }

    public Card addCard(Card card) {
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(INSERT_CARD)) {

            statement.setInt(1, card.getOwner_id());
            statement.setString(2, card.getCard_number());
            statement.setString(3, card.getCard_name());
            statement.setString(4, card.getCard_expiry_date());
            statement.setInt(5, card.getCard_cvv());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Card(resultSet);
            }else {
                throw new IllegalArgumentException();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();

        try (Connection connect = databaseConfig.connect()) {

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CARDS_LIST);

            while (resultSet.next()) {
                Card card = new Card(resultSet);
                cards.add(card);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public List<Card> getCardsByOwnerId(int userId) {
        List<Card> cards = new ArrayList<>();

        try (Connection connect = databaseConfig.connect()) {

            PreparedStatement statement = connect.prepareStatement(GET_CARDS_BY_OWNER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card(resultSet);
                cards.add(card);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public Card getCardById(int cardId) {
        try (Connection connect = databaseConfig.connect()) {

            PreparedStatement statement = connect.prepareStatement(GET_CARD_BY_ID);
            statement.setInt(1, cardId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Card(resultSet);
            } else {
                throw new IllegalArgumentException();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Card deleteCardById(int cardId) {
        try (Connection connect = databaseConfig.connect()) {

            PreparedStatement statement = connect.prepareStatement(DELETE_CARD_BY_ID);
            statement.setInt(1, cardId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Card(resultSet);
            } else {
                throw new IllegalArgumentException();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isCardExisting(String cardNumber) {
        try (Connection connect = databaseConfig.connect()) {

            PreparedStatement statement = connect.prepareStatement(IS_CARD_EXISTING);
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}