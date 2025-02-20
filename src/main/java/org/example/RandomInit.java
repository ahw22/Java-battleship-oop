package org.example;

import org.example.model.Carrier;
import org.example.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class RandomInit implements BoardInitializer{
    @Override
    public Board initBoard(Player player) {
        Board board = new Board();
        Ship testShip = new Carrier();
        System.out.println(testShip.toStringLine());
        placeShip(testShip, board);
        return null;
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
