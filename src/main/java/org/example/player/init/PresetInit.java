package org.example.player.init;

import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;


public class PresetInit implements BoardInitializer{
    private Board board;

    public PresetInit(Board board) {
        this.board = board;
    }

    @Override
    public Board initBoard(Player player) {
        while (!player.getShipsToPlace().isEmpty()) {
        Ship peek = player.getShipsToPlace().peek();
        char targetRow = (char) ('A' + peek.getHP());
        String start = String.valueOf(targetRow + 1);
        String end = String.valueOf(targetRow + peek.getHP());
        board.placeShip(convertStringToPosition(start), convertStringToPosition(end), player);
        }
        board.printBoard();
        return board;
    }
}
