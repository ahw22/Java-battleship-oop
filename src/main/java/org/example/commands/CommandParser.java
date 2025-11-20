package org.example.commands;

import org.example.model.board.Position;

public class CommandParser {
    public Command parse(String input) {
        String[] args = input.trim().split("\\s+");
        if (args.length == 0) return new UnknownCommand(input);

        String cmd = args[0].toLowerCase();

        return switch (cmd) {
            case "fire" -> (args.length == 2)
                    ? new FireCommand(convertStringToPosition(args[1]))
                    : new UnknownCommand(input);

            case "place" -> (args.length == 3)
                    ? new PlaceShipCommand(convertStringToPosition(args[1]), convertStringToPosition(args[2]))
                    : new UnknownCommand(input);

            case "show" -> new ShowCommand();
            case "quit" -> new QuitCommand();
            default -> new UnknownCommand(input);
        };

    }

    public Position convertStringToPosition(String input) {
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

        return new Position(column, row);
    }
}
