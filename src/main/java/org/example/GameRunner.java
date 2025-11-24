package org.example;

import org.example.commands.Command;
import org.example.commands.CommandParser;
import org.example.game.GameContext;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameRunner {
    private final Scanner scanner;
    private final PrintStream out;

    public GameRunner(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public void run(GameContext context, CommandParser commandParser) {
        while(true) {
            out.print(context.getCurrentPlayer().getName() + "> ");
            String input = scanner.nextLine();

            Command cmd = commandParser.parse(input);
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
