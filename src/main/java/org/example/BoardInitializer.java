package org.example;

import org.example.model.Ship;

public interface BoardInitializer {
    Board initBoard(Player player);
    void placeShip(Ship ship, Board board);

}
