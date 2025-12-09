package org.example.commands;

import org.example.game.GameContext;

public class UnknownCommand extends AbstractCommand implements Command {
    private final String input;

    public UnknownCommand(String input) {
        super(null, 0, "");
        this.input = input;
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return true;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.printLine("Command '" + input + "' is not a valid command. Use 'help' to see available commands.");
    }
}
