package org.example.commands;

import org.example.game.GameContext;

public class UnknownCommand implements Command{
    private final String input;

    public UnknownCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(GameContext context) {
        System.out.println("Command '" + input + "' is not a valid command.");
    }
}
