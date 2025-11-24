package org.example.commands;

import lombok.AllArgsConstructor;
import org.example.game.GameContext;

@AllArgsConstructor
public class UnknownCommand implements Command{
    private final String input;

    @Override
    public void execute(GameContext context) {
        System.out.println("Command '" + input + "' is not a valid command.");
    }
}
