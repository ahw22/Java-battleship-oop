package org.example.commands;

import org.example.game.GameContext;

public interface Command {

    boolean matches(String keyword, String[] args);
    void execute(GameContext context, String[] args);
    String getHelpText();
}
