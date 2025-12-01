package org.example.input;

import org.example.commands.Command;
import org.example.commands.CommandParser;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandParser commandParser = new CommandParser();

    @Override
    public Command getNextCommand() {
        System.out.print("> ");
        String input = scanner.nextLine();
        return commandParser.parse(input);
    }
}
