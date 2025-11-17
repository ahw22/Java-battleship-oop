package org.example.player.init;

import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;


public class PresetInit implements BoardInitializer{
    private Board board;

    public PresetInit() {
        this.board = new Board();
    }

    @Override
    public Board initBoard(Player player) {
        while (!player.getShipsToPlace().isEmpty()) {
        Ship peek = player.getShipsToPlace().peek();
        char targetRow = (char) ('A' + peek.getHP());
        String start = "" +targetRow + '1';
        String end = "" + targetRow + peek.getHP();
        board.placeShip(convertStringToPosition(start), convertStringToPosition(end), player);
        }
        board.printBoard();
        return board;
    }

    private Position convertStringToPosition(String input) {
        input = input.toUpperCase();

        if (input.length() != 2) {
            throw new IllegalArgumentException("Input is invalid length! Please enter a letter followed by a single digit.");
        }

        // Extract column and row
        char columnChar = input.charAt(0);
        String rowPart = input.substring(1);

        // Validate row is a single digit (0-9)
        if (!rowPart.matches("[0-9]")) {
            throw new ArithmeticException("Only use a single digit (0-9) for your Row.");
        }

        int row = rowPart.charAt(0) - '0';
        int column = columnChar - 'A';

        // Validate column (A-J)
        if (column < 0 || column > 9) {
            throw new ArithmeticException("Only use letters A through J for your Column.");
        }

        return new Position(row, column);
    }
}
