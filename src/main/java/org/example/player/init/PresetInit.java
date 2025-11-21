package org.example.player.init;

import org.example.commands.CommandParser;
import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;


public class PresetInit implements BoardInitializer {
    private Board board;
    private CommandParser commandParser = new CommandParser();

    public PresetInit() {
        this.board = new Board();
    }

    @Override
    public Board initBoard(Player player) {
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
