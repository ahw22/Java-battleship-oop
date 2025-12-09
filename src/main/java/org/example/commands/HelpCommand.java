package org.example.commands;

import org.example.game.GameContext;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        this.key = "help";
        this.argsCount = 1;
        this.helpText = "help:\t used to show this list of commands.";
    }

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
