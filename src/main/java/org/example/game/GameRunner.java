package org.example.game;

import org.example.commands.Command;
import org.example.commands.CommandParser;

import java.io.PrintStream;

public class GameRunner {
    private final PrintStream out;

    public GameRunner(PrintStream out) {
        this.out = out;
    }

    public void run(GameContext context, CommandParser commandParser) {
        while(true) {
            out.print(context.getCurrentPlayer().getName() + "> ");
            Command cmd = context.getCurrentPlayer().getNextCommand();

            if (cmd == null) {
                System.out.println("Unknown command: Type 'help' to see the available options");
                continue;
            }

            cmd.execute(context);

            if (context.isGameOver()) {
                context.gameOver();
                break;
            }
        }
    }
}
