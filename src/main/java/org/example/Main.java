package org.example;

import org.example.game.GameContext;
import org.example.model.board.Position;
import org.example.player.Player;
import org.example.player.init.PresetInit;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(new PresetInit(), "User");
        Player player2 = new Player(new PresetInit(), "CPU");
        player1.setTargetBoard(player2.getOwnBoard());
        player2.setTargetBoard(player1.getOwnBoard());

        GameContext context = new GameContext(player1, player2);
        context.showBoards();
        context.fireAt(new Position(2,2));
        context.fireAt(new Position(2,1));
        context.showBoards();
    }
}
