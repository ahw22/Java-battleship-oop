package org.example;

import org.example.input.UserInputHandler;
import org.example.player.Player;
import org.example.player.init.ManualInit;

public class Main {
    public static void main(String[] args) {
        UserInputHandler inputHandler = new UserInputHandler(System.in);
        ManualInit manualInit = new ManualInit(inputHandler);

        Player player1 = new Player(manualInit); // inject the board initializer

        player1.getBoard().printBoard(); // assuming your Board has a printBoard method
    }
}
