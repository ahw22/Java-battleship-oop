package org.example.player;

import org.example.commands.ParsedCommand;
import org.example.input.InputHandler;
import org.example.player.init.BoardInitializer;

public class RandomPlayer extends AbstractPlayer{

    public RandomPlayer(BoardInitializer initializer, InputHandler inputHandler, String name) {
        super(initializer, inputHandler, name);
    }

    @Override
    public ParsedCommand getNextCommand() {
        return inputHandler.getNextCommand();
    }
}
