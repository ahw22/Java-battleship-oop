package org.example.commands;

import org.example.game.GameContext;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        this.key = "show";
        this.argsCount = 1;
        this.helpText = "show:\t used to show your board.";
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.showOwnBoard();
    }
}
