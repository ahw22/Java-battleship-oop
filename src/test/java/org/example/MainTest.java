package org.example;

import org.example.commands.CommandParser;
import org.example.game.GameContext;
import org.example.game.GameRunner;
import org.example.player.Player;
import org.example.player.init.PresetInit;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testFullGame() {
        String input = String.join("\n",
                "fire B1",
                "fire A0",
                "fire C1",
                "fire A1",
                "fire D1",
                "fire A2",
                "fire E1",
                "fire A3",
                "fire F1",
                "fire A4",
                "fire B2",
                "fire A5",
                "fire C2",
                "fire A6",
                "fire D2",
                "fire A7",
                "fire E2",
                "fire A8",
                "fire B3",
                "fire A9",
                "fire C3",
                "fire B0",
                "fire D3",
                "fire C0",
                "fire B4",
                "fire D0",
                "fire C4"
        );

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBytes);

        Player p1 = new Player(new PresetInit(), "Player");
        Player p2 = new Player(new PresetInit(), "CPU");
        p1.setTargetBoard(p2.getOwnBoard());
        p2.setTargetBoard(p1.getOwnBoard());

        GameContext ctx = new GameContext(p1, p2);
        CommandParser commandParser = new CommandParser();

        GameRunner runner = new GameRunner(in, out);
        runner.run(ctx, commandParser);

        // Now inspect output
        String output = outBytes.toString();

        assertTrue(output.contains("hit"));
        assertTrue(output.contains("Player has won the game!"));
    }

}
