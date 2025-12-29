package org.example.commands;

import org.example.game.Game;

public interface Command {

    boolean matches(String keyword, String[] args);
    void execute(Game context, String[] args);
    String getHelpText();
}
