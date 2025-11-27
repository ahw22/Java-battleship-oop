package org.example;

import org.example.commands.CommandParser;
import org.example.game.GameContext;
import org.example.game.GameRunner;
import org.example.player.AbstractPlayer;
import org.example.player.Player;
import org.example.player.init.PresetInit;

public class Main {
    public static void main(String[] args) {
        AbstractPlayer player1 = new Player(new PresetInit(), "Player");
        AbstractPlayer player2 = new Player(new PresetInit(), "CPU");
        player1.setTargetBoard(player2.getOwnBoard());
        player2.setTargetBoard(player1.getOwnBoard());

        GameContext context = new GameContext(player1, player2);
        CommandParser commandParser = new CommandParser();

        System.out.println("Welcome to Battleship!");
        System.out.println("Type 'help' for commands.");
        context.showTarget();

        GameRunner runner = new GameRunner(System.in, System.out);
        runner.run(context, commandParser);
    }
}
