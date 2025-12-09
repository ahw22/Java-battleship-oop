package org.example.game;

import org.example.commands.ParsedCommand;

import java.io.PrintStream;

public class GameRunner {
    private final PrintStream out;

    public GameRunner(PrintStream out) {
        this.out = out;
    }

    public void run(GameContext context) {
        context.printLine("Welcome to Battleship!");
        context.printLine("Type 'help' for commands.");
        context.showTarget();
        while(true) {
            try {

            out.print(context.getCurrentPlayer().getName() + "> ");
            ParsedCommand cmd = context.getCurrentPlayer().getNextCommand();

            if (cmd == null) {
                out.println("Unknown command: Type 'help' to see the available options");
                continue;
            }

            cmd.command().execute(context, cmd.args());

            if (context.isGameOver()) {
                context.gameOver();
                break;
            }
            } catch (Exception e) {
                context.printLine("Error: " + e.getMessage());
            }
        }
    }
}
