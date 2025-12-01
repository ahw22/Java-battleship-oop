package org.example.commands;

import lombok.Getter;
import org.example.game.GameContext;

public class HelpCommand implements Command{
    private final String key = "help";
    private final int argsCount = 1;
    @Getter
    private final String helpText = "help:\t used to show this list of commands.";

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        CommandParser parser = new CommandParser();
        context.printLine("The following commands are available: ");
        parser.getCommandList().forEach(command -> context.printLine(command.getHelpText()));
    }
}
