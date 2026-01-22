package org.example;

import org.example.game.Game;
import org.example.game.GameRunner;
import org.example.init.RandomInit;
import org.example.input.ConsoleInputHandler;
import org.example.input.RandomInputHandler;
import org.example.output.BufferedOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.example.player.Player;
import org.example.player.RandomPlayer;

public class Main {
    public static void main(String[] args) {
        AbstractPlayer player1 = new Player(new RandomInit(), new ConsoleInputHandler(), "Player");
        AbstractPlayer player2 = new RandomPlayer(new RandomInit(), new RandomInputHandler(), "CPU");
        player1.setTargetBoard(player2.getOwnBoard());
        player2.setTargetBoard(player1.getOwnBoard());

        OutputControllerInterface out = new BufferedOutputController();

        Game context = new Game(player1, player2, out);
        GameRunner runner = new GameRunner(out);
        runner.run(context);
    }
}
