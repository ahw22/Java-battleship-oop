package org.example.player.init;

import org.example.model.board.Board;
import org.example.model.ship.Ship;
import org.example.player.Player;

public interface BoardInitializer {
    Board initBoard(Player player);
    void placeShip(Ship ship, Board board);

}
