package org.example.commands;

import org.example.game.Game;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show", 1, "show:\t used to show your board.");
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return getKey().matches(keyword) && args.length == getArgsCount();
    }

    @Override
    public void execute(Game context, String[] args) {
        context.showOwnBoard();
    }
}
