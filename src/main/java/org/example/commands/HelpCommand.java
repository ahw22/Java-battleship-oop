package org.example.commands;

import org.example.game.GameContext;

public class HelpCommand implements Command{
    @Override
    public void execute(GameContext context) {
        System.out.println("""
                The following commands are available:
                help: used to show this list of commands.
                fire: used to fire at enemy board. Example: fire B3
                show: used to show both your and the enemy board.
                place: used when placing ships before the game. Choose start and endpoint of your ship. Example: place A3 C6
                quit: Exits the game.
                """);;
    }
}
