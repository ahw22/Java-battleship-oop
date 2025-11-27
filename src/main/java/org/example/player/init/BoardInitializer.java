package org.example.player.init;

import org.example.model.board.Board;
import org.example.player.AbstractPlayer;

public interface BoardInitializer {
    Board initBoard(AbstractPlayer player);
}
