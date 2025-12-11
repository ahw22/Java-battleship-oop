package org.example;

import org.example.game.GameContext;
import org.example.game.GameRunner;
import org.example.input.RandomInputHandler;
import org.example.output.ConsoleOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.Player;
import org.example.init.PresetInit;
import org.junit.jupiter.api.Test;

class RandomTest {

    @Test
    void testFullGame() {
        Player p1 = new Player(new PresetInit(), new RandomInputHandler(), "CPU1");
        Player p2 = new Player(new PresetInit(), new RandomInputHandler(), "CPU2");
        p1.setTargetBoard(p2.getOwnBoard());
        p2.setTargetBoard(p1.getOwnBoard());

        OutputControllerInterface out = new ConsoleOutputController();

        GameContext ctx = new GameContext(p1, p2, out);

        GameRunner runner = new GameRunner();
        runner.run(ctx);

    }

}
