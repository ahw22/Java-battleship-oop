package org.example.player;

import lombok.Getter;
import org.example.commands.ParsedCommand;
import org.example.game.Game;
import org.example.input.InputHandler;
import org.example.init.BoardInitializer;

@Getter
public class Player extends AbstractPlayer {

    public Player(BoardInitializer initializer, InputHandler inputHandler, String name) {
        super(initializer, inputHandler, name);
    }

    @Override
    public ParsedCommand getNextCommand(Game context) {
        context.showTarget();
        context.printNow(getName() + "> ");
        return inputHandler.getNextCommand();
    }

}
