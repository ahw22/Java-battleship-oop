package org.example.input;

import org.example.commands.CommandParser;
import org.example.commands.ParsedCommand;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class TestInputHandler implements InputHandler {
    private final Scanner in;
    private final CommandParser parser;

    public TestInputHandler(String input) {
        this.in = new Scanner(new ByteArrayInputStream(input.getBytes()));
        this.parser = new CommandParser();
    }

    @Override
    public ParsedCommand getNextCommand() {
        return parser.parse(in.nextLine());
    }
}
