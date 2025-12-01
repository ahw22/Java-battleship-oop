package org.example.input;

import org.example.commands.CommandParser;
import org.example.commands.ParsedCommand;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandParser commandParser = new CommandParser();

    @Override
    public ParsedCommand getNextCommand() {
        String input = scanner.nextLine();
        return commandParser.parse(input);
    }
}
