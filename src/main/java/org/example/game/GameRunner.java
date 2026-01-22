package org.example.game;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.commands.ParsedCommand;
import org.example.output.OutputControllerInterface;

@NoArgsConstructor
@AllArgsConstructor
public class GameRunner {
    private OutputControllerInterface out;

    public void run(Game context) {
        context.printLine("Welcome to Battleship!");
        context.printLine("Type 'help' for a list of commands.");
        
        // Initial flush to show welcome message
        if (out != null) out.flush();

        while (true) {
            try {
                ParsedCommand cmd = context.getNextCommand();

                if (cmd == null) {
                    context.printLine("Unknown command: Type 'help' to see the available options");
                    if (out != null) out.flush();
                    continue;
                }

                cmd.command().execute(context, cmd.args());

                if (out != null) out.flush();

                if (context.isGameOver()) {
                    break;
                }
            } catch (Exception e) {
                context.printLine("Error: " + e.getMessage());
                if (out != null) out.flush();
            }
        }
    }
}
