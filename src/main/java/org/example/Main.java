package org.example;

import org.example.game.GameContext;
import org.example.game.GameRunner;
import org.example.input.ConsoleInputHandler;
import org.example.input.RandomInputHandler;
import org.example.output.ConsoleOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.example.player.Player;
import org.example.player.RandomPlayer;
import org.example.player.init.PresetInit;

public class Main {
    public static void main(String[] args) {
        AbstractPlayer player1 = new Player(new PresetInit(), new ConsoleInputHandler(), "Player");
//        AbstractPlayer player2 = new Player(new PresetInit(), new ConsoleInputHandler(), "CPU");
        AbstractPlayer player2 = new RandomPlayer(new PresetInit(), new RandomInputHandler(), "CPU");
        player1.setTargetBoard(player2.getOwnBoard());
        player2.setTargetBoard(player1.getOwnBoard());

        OutputControllerInterface out = new ConsoleOutputController();

        GameContext context = new GameContext(player1, player2, out);
        GameRunner runner = new GameRunner();
        runner.run(context);
    }
}
