package org.example.commands;

import org.example.game.GameContext;

public class ShowCommand implements Command{
    @Override
    public void execute(GameContext context) {
        context.showBoards();
    }
}
