package org.example.commands;

import org.example.game.Game;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", 1, "help:\t used to show this list of commands.");
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return getKey().matches(keyword) && args.length == getArgsCount();
    }

    @Override
    public void execute(Game context, String[] args) {
        CommandParser parser = new CommandParser();
        context.printLine("The following commands are available: ");
        parser.getCommandList().forEach(command -> context.printLine(command.getHelpText()));
    }
}
