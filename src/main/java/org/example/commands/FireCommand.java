package org.example.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.game.GameContext;
import org.example.model.board.Position;

@NoArgsConstructor
public class FireCommand implements Command {
    private final String key = "fire";
    private final int argsCount = 2;
    @Getter
    private final String helpText = "fire:\t used to fire at enemy board. Example: fire B3";

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
            System.out.println(hit ? "Hit at " + target + "!" : "Miss at " + target + ".");
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
