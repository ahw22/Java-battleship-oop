package org.example.commands;


public class CommandParser {
    public Command parse(String input) {
        String[] args = input.trim().split("\\s+");
        if (args.length == 0) return new UnknownCommand(input);

        String cmd = args[0].toLowerCase();

        return switch (cmd) {
            case "fire" -> (args.length == 2)
                    ? new FireCommand(args[1])
                    : new UnknownCommand(input);

            case "place" -> (args.length == 3)
                    ? new PlaceShipCommand(args[1], args[2])
                    : new UnknownCommand(input);

            case "show" -> new ShowCommand();
            case "quit" -> new QuitCommand();
            default -> new UnknownCommand(input);
        };
    }
}
