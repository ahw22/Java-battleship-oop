package org.example;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        Player player1 = new Player();
        UserInputHandler inputHandler = new UserInputHandler(System.in);
        inputHandler.getPosFromUser();
    }
}