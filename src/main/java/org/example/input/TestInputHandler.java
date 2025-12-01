package org.example.input;

import org.example.commands.Command;
import org.example.commands.CommandParser;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class TestInputHandler implements InputHandler{
    Scanner in;
    CommandParser parser;

    public TestInputHandler(String input) {
        this.in = new Scanner(new ByteArrayInputStream(input.getBytes()));
        this.parser = new CommandParser();
    }

    @Override
    public Command getNextCommand() {
        return parser.parse(in.nextLine());
    }
}
