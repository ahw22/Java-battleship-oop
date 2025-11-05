package org.example.game;

import org.example.model.board.Board;
import org.example.player.Player;

import java.util.ArrayList;

public class GameContext {
    private ArrayList<Player> players;
    private final ArrayList<Board> boards;
    private Player player;
    private Player opponent;

    public GameContext(ArrayList<Player> players, ArrayList<Board> boards, Player player, Player opponent) {
        this.players = players;
        this.boards = boards;
        this.player = player;
        this.opponent = opponent;
    }

    
}
