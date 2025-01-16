package org.example;

public class ManualInit implements BoardInitializer {

    public Board initBoard(Player player) {
        Board board = new Board();
        Ship testShip = new Destroyer();
        placeShip(testShip, board);
        return board;
    }

    @Override
    public void placeShip(Ship ship, Board board) {

    }

}
