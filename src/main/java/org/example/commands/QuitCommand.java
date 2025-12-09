package org.example.commands;

import org.example.game.GameContext;

public class QuitCommand extends AbstractCommand {

    public QuitCommand() {
        this.key = "quit";
        this.argsCount = 1;
        this.helpText = "quit:\t exits the game.";
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.quit();
    }

}
