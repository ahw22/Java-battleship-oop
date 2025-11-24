package org.example.commands;

import org.example.game.GameContext;
import org.example.model.board.Position;

public class FireCommand implements Command {
    private final Position target;

    public FireCommand(Position target) {
        this.target = target;
    }

    @Override
    public void execute(GameContext context) {
        boolean hit = false;
        try {
            hit = context.fireAt(target);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(hit ? "Hit at " + target + "!" : "Miss at " + target + ".");
        context.nextTurn();
    }

}
