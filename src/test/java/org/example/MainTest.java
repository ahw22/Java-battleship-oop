package org.example;

import org.example.game.GameContext;
import org.example.game.GameRunner;
import org.example.input.TestInputHandler;
import org.example.output.ConsoleOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.Player;
import org.example.init.PresetInit;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testFullGame() {
        String input = String.join("\n",
                "fire B1",
                "fire C1",
                "fire D1",
                "fire E1",
                "fire F1",
                "fire B2",
                "fire C2",
                "fire D2",
                "fire E2",
                "fire B3",
                "fire C3",
                "fire D3",
                "fire B4",
                "fire C4"
        );
        String input2 = String.join("\n",
                "fire A0",
                "fire A1",
                "fire A2",
                "fire A3",
                "fire A4",
                "fire A5",
                "fire A6",
                "fire A7",
                "fire A8",
                "fire A9",
                "fire B0",
                "fire C0",
                "fire D0"
        );

        Player p1 = new Player(new PresetInit(), new TestInputHandler(input), "Player");
        Player p2 = new Player(new PresetInit(), new TestInputHandler(input2), "CPU");
        p1.setTargetBoard(p2.getOwnBoard());
        p2.setTargetBoard(p1.getOwnBoard());

        OutputControllerInterface out = new ConsoleOutputController();

        GameContext ctx = new GameContext(p1, p2, out);

        GameRunner runner = new GameRunner();
        runner.run(ctx);


    }
}
