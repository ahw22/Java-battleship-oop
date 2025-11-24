package org.example.commands;

import lombok.AllArgsConstructor;
import org.example.game.GameContext;
import org.example.model.board.Position;

@AllArgsConstructor
public class FireCommand implements Command {
    private final Position target;

    @Override
    public void execute(GameContext context) {
        boolean hit;
        try {
            hit = context.fireAt(target);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(hit ? "Hit at " + target + "!" : "Miss at " + target + ".");
        if (context.isGameOver()) {
            context.gameOver();
            return;
        }
        context.nextTurn();
    }

}
