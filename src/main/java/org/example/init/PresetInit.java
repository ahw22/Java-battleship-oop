package org.example.init;

import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.AbstractPlayer;


public class PresetInit implements BoardInitializer {
    private final Board board;

    public PresetInit() {
        this.board = new Board();
    }

    @Override
    public Board initBoard(AbstractPlayer player) {
        int rowCounter = 1;
        while (!player.getShipsToPlace().isEmpty()) {
            Ship peek = player.getShipsToPlace().peek();
            Position start = new Position(1, rowCounter);
            Position end = new Position(1 + peek.getHP() - 1, rowCounter);
            board.placeShip(start, end, player);
            rowCounter++;
            player.getShipsToPlace().poll();
        }
        return board;
    }
}
