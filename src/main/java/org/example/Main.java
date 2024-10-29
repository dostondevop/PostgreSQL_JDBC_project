package org.example;

import org.example.dao.UserDao;
import org.example.model.Card;
import org.example.model.User;
import org.example.service.CardService;
import org.example.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        CardService cardService = new CardService();

//        User user = userService.registerUser(new User(1, "A", "aaa", "Rf^oot123s4", "emaq1il_s@gmail.com", "2024-10-26", "2024-10-10", true));
//        System.out.println(user);
//        User user = userService.getUserById(3);
//        System.out.println(user);

//        Card card = cardService.addCard(new Card(1, 3, "1223456", "VISA", "1234567890", 1234567, "1234567890", "1234567890", true));
//        System.out.println(card);

//        System.out.println(userService.signIn("aaa1", "Rf^oot123s4"));
//        System.out.println(cardService.getCardsByOwnerId(3));
        System.out.println(cardService.deleteCardById(7));
    }
}