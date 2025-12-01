package org.example.player;

import lombok.Getter;
import org.example.commands.Command;
import org.example.input.InputHandler;
import org.example.player.init.BoardInitializer;

@Getter
public class Player extends AbstractPlayer{

    public Player(BoardInitializer initializer, InputHandler inputHandler, String name) {
        super(initializer,inputHandler, name);
    }

    @Override
    public Command getNextCommand() {
        return inputHandler.getNextCommand();
    }

}
