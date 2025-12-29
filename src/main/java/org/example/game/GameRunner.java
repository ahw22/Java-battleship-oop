package org.example.game;

import lombok.NoArgsConstructor;
import org.example.commands.ParsedCommand;

@NoArgsConstructor
public class GameRunner {

    public void run(Game context) {
        context.printLine("Welcome to Battleship!");
        context.printLine("Type 'help' for a list of commands.");
        while (true) {
            try {
                ParsedCommand cmd = context.getNextCommand();

                if (cmd == null) {
                    context.printLine("Unknown command: Type 'help' to see the available options");
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
