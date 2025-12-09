package org.example.commands;

import org.example.game.GameContext;
import org.example.model.board.Position;

public class FireCommand extends AbstractCommand {

    public FireCommand() {
        this.key = "fire";
        this.argsCount = 2;
        this.helpText = "fire:\t used to fire at enemy board. Example: fire B3";
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        boolean hit;
        try {
            Position target = context.convertStringToPosition(args[1]);
            hit = context.fireAt(target);
            context.printLine(hit ? "Hit at " + target + "!" : "Miss at " + target + ".");
            if (context.isGameOver()) {
                context.gameOver();
                return;
            }
            context.nextTurn();
        } catch (IllegalStateException | IllegalArgumentException e) {
            context.printLine(e.getMessage());
        }
    }
}
