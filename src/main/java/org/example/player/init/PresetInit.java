package org.example.player.init;

import org.example.commands.CommandParser;
import org.example.model.board.Board;
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
        while (!player.getShipsToPlace().isEmpty()) {
            Ship peek = player.getShipsToPlace().peek();
            char targetRow = (char) ('A' + peek.getHP());
            String start = "" + targetRow + '1';
            String end = "" + targetRow + peek.getHP();
            board.placeShip(commandParser.convertStringToPosition(start), commandParser.convertStringToPosition(end), player);
        }
        return board;
    }
}
