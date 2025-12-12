package org.example.init;

import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.AbstractPlayer;

import java.util.Random;

public class RandomInit implements BoardInitializer {
    private final Board board;

    public RandomInit() {
        this.board = new Board();
    }

    @Override
    public Board initBoard(AbstractPlayer player) {
        Random random = new Random();
        while (!player.getShipsToPlace().isEmpty()) {
            Ship ship = player.getShipsToPlace().peek();
            Position start = new Position();
            Position end;
            if (board.getFieldFromPosition(start).isOccupied()) {
                continue;
            }
            boolean horizontal = random.nextBoolean();
            try {
                if (horizontal) {
                    end = new Position(start.getColumn() + ship.getHP() - 1, start.getRow());
                } else {
                    end = new Position(start.getColumn(), start.getRow() + ship.getHP() - 1);
                }
                board.placeShip(start, end, player);
            } catch (Exception e) {
                continue;
            }
            player.getShipsToPlace().poll();
        }
        return board;
    }

}
