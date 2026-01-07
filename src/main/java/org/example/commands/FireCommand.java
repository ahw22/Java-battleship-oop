package org.example.commands;

import org.example.game.Game;
import org.example.model.board.Position;

public class FireCommand extends AbstractCommand {

    public FireCommand() {
        super("fire", 2, "fire:\t used to fire at enemy board. Example: fire B3");
    }

    @Override
    public boolean matches(String keyword, String[] args) {
        return getKey().matches(keyword) && args.length == getArgsCount();
    }

    @Override
    public void execute(Game context, String[] args) {
        try {
            Position target = context.convertStringToPosition(args[1]);
            context.fireAt(target);
        } catch (IllegalStateException | IllegalArgumentException e) {
            context.printLine(e.getMessage());
        }
    }
}
