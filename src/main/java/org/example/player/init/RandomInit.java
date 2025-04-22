package org.example.player.init;

import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.List;

public class RandomInit implements BoardInitializer{

    @Override
    public Board initBoard(Player player) {
        Board board = new Board();
        for (Ship ship : player.getShips()) {
            placeShip(ship, board);
        }
        return board;
    }

    public void placeShip(Ship ship, Board board) {
        List<Position> validPositions = new ArrayList<>();
        findValidPosition(validPositions, ship);
    }

    private void findValidPosition(List<Position> validPositions, Ship ship) {
        /*
        we get a random position, then check in all directions if there is enough room for a ship to be placed in that
        direction.
         */
        int length = ship.getHP();
        Position startPos = new Position();
    }
}
