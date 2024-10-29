package org.example.service;

import org.example.dao.CardDao;
import org.example.model.Card;
import org.example.model.User;

import java.util.List;

public class CardService {

    private CardDao cardDao;

    public CardService() {
        this.cardDao = new CardDao();
    }

    public Card addCard(Card card) {
        if (cardDao.isCardExisting(card.getCard_number())) {
            throw new IllegalArgumentException("Card with this number already exists");
        }
        return cardDao.addCard(card);
    }

    public List<Card> getCardsByOwnerId(int ownerId) {
        return cardDao.getCardsByOwnerId(ownerId);
    }

    public Card getCardById(int cardId) {
        return cardDao.getCardById(cardId);
    }

    public List<Card> getCards() {
        return cardDao.getAllCards();
    }

    public Card deleteCardById(int cardId) {
        return cardDao.deleteCardById(cardId);
    }
}
