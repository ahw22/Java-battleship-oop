package org.example.game;

import lombok.NoArgsConstructor;
import org.example.commands.ParsedCommand;

@NoArgsConstructor
public class GameRunner {

    public void run(GameContext context) {
        context.printLine("Welcome to Battleship!");
        context.printLine("Type 'help' for commands.");
        context.showTarget();
        while(true) {
            try {

            context.print(context.getCurrentPlayer().getName() + "> ");
            ParsedCommand cmd = context.getCurrentPlayer().getNextCommand();

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
