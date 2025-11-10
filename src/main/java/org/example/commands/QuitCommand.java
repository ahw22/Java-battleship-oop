package org.example.commands;

import org.example.game.GameContext;

public class QuitCommand implements Command{
    @Override
    public void execute(GameContext context) {
        context.quit();
    }
}
