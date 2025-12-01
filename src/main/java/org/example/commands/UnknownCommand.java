package org.example.commands;

import lombok.AllArgsConstructor;
import org.example.game.GameContext;

@AllArgsConstructor
public class UnknownCommand implements Command {
    private final String input;

    @Override
    public boolean matches(String keyword, String[] args) {
        return true;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.printLine("Command '" + input + "' is not a valid command.");
    }

    @Override
    public String getHelpText() {
        return "";
    }
}
