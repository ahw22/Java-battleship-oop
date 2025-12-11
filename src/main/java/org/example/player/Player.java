package org.example.player;

import lombok.Getter;
import org.example.commands.ParsedCommand;
import org.example.input.InputHandler;
import org.example.init.BoardInitializer;

@Getter
public class Player extends AbstractPlayer {

    public Player(BoardInitializer initializer, InputHandler inputHandler, String name) {
        super(initializer, inputHandler, name);
    }

    @Override
    public ParsedCommand getNextCommand() {
        return inputHandler.getNextCommand();
    }

}
