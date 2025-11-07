package org.example.player;

import lombok.Builder;
import lombok.Getter;
import org.example.player.init.BoardInitializer;
import org.example.model.board.Board;
import org.example.model.ship.*;

@Getter
@Builder
public class Player {
    private final String name;
    private final Board ownBoard;
    private final Board targetBoard;

    public Player(BoardInitializer initializer, String name) {
        this.ownBoard = initializer.initBoard(this);
        this.targetBoard = new Board();
        this.name = name;
    }

}
