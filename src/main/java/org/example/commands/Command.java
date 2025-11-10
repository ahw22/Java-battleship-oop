package org.example.commands;

import org.example.game.GameContext;

public interface Command {

    void execute(GameContext context);
}
