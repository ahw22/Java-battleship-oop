package org.example.commands;

import org.example.model.board.Position;

public class CommandParser {
    public Command parse(String input) {
        if (input == null) return new UnknownCommand(input);
        String[] args = input.trim().split("\\s+");

        String cmd = args[0].toLowerCase();

        return switch (cmd) {
            case "fire" -> {
                try {
                    if (args.length != 2) {
                        yield new UnknownCommand(input);
                    }
                    yield new FireCommand(convertStringToPosition(args[1]));
                } catch (IllegalArgumentException e) {
                    yield new UnknownCommand(input);
                }
            }
            case "place" -> {
                try {
                    if (args.length != 3) {
                        yield new UnknownCommand(input);
                    }
                    yield new PlaceShipCommand(convertStringToPosition(args[1]), convertStringToPosition(args[2]));
                } catch (IllegalArgumentException e) {
                    yield new UnknownCommand(input);
                }
            }
            case "show" -> new ShowCommand();
            case "quit" -> new QuitCommand();
            case "help" -> new HelpCommand();
            default -> new UnknownCommand(input);
        };
    }

    public Position convertStringToPosition(String input) {
        input = input.toUpperCase();

        if (input.length() != 2) {
            throw new IllegalArgumentException(
                    "Input is invalid length! Please enter a letter followed by a single digit.");
        }

        // Extract column and row
        char columnChar = input.charAt(0);
        String rowPart = input.substring(1);

        // Validate row is a single digit (0-9)
        if (!rowPart.matches("[0-9]")) {
            throw new IllegalArgumentException("Only use a single digit (0-9) for your Row.");
        }

        int row = rowPart.charAt(0) - '0';
        int column = columnChar - 'A';

        // Validate column (A-J)
        if (column < 0 || column > 9) {
            throw new IllegalArgumentException("Only use letters A through J for your Column.");
        }

        return new Position(column, row);
    }
}
