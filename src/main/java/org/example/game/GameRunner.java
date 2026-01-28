package org.example.game;

import lombok.AllArgsConstructor;
import org.example.commands.ParsedCommand;
import org.example.output.OutputControllerInterface;

@AllArgsConstructor
public class GameRunner {
    private OutputControllerInterface out;

    public void run(Game game) {
        game.printLine("Welcome to Battleship!");
        game.printLine("Type 'help' for a list of commands.");
        
        // Initial flush to show welcome message
        out.flush();

        while (!game.isGameOver()) {
            try {
                ParsedCommand cmd = game.getNextCommand();

                if (cmd == null) {
                    game.printError("Unknown command: Type 'help' to see the available options");
                    out.flush();
                    continue;
                }

                cmd.command().execute(game, cmd.args());

                out.flush();

            } catch (Exception e) {
                game.printError("Error: " + e.getMessage());
                out.flush();
            }
        }
    }
}
