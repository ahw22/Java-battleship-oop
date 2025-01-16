package org.example;

public interface BoardInitializer {
    Board initBoard(Player player);
    void placeShip(Ship ship, Board board);

}
