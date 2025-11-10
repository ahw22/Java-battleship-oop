package org.example.commands;

import org.example.game.GameContext;

public class FireCommand implements Command {
    private final String target;

    public FireCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(GameContext context) {
        boolean hit = context.fireAt(target);
        System.out.println(hit ? "Hit at " + target + "!" : "Miss at " + target + ".");
        context.nextTurn();
    }

}
