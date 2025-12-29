package org.example.commands;

import org.example.game.Game;

public class QuitCommand extends AbstractCommand {

    public QuitCommand() {
        super("quit", 1, "quit:\t exits the game.");
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return getKey().matches(keyword) && args.length == getArgsCount();
    }

    @Override
    public void execute(Game context, String[] args) {
        context.quit();
    }

}
