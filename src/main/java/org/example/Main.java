package org.example;

import org.example.game.GameContext;
import org.example.player.Player;
import org.example.player.init.PresetInit;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(new PresetInit(), "User");
        Player player2 = new Player(new PresetInit(), "CPU");

        GameContext context = new GameContext(player1, player2);
    }
}
