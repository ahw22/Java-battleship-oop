package org.example.player;

import lombok.Getter;
import org.example.commands.Command;
import org.example.player.init.BoardInitializer;

@Getter
public class Player extends AbstractPlayer{

    public Player(BoardInitializer initializer, String name) {
        super(initializer, name);
    }

    @Override
    public Command getNextCommand() {
        return null;
    }

}
