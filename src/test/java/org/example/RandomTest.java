package org.example;

import org.example.game.Game;
import org.example.game.GameRunner;
import org.example.init.RandomInit;
import org.example.input.RandomInputHandler;
import org.example.output.BufferedOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.example.player.RandomPlayer;
import org.junit.jupiter.api.Test;

class RandomTest {

    @Test
    void testFullGame() {
        AbstractPlayer p1 = new RandomPlayer(new RandomInit(), new RandomInputHandler(), "CPU1");
        AbstractPlayer p2 = new RandomPlayer(new RandomInit(), new RandomInputHandler(), "CPU2");
        p1.setTargetBoard(p2.getOwnBoard());
        p2.setTargetBoard(p1.getOwnBoard());

        OutputControllerInterface out = new BufferedOutputController();

        Game ctx = new Game(p1, p2, out);

        GameRunner runner = new GameRunner(out);
        runner.run(ctx);

    }

}
