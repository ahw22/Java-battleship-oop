package org.example.commands;

import org.example.game.Game;
import org.example.model.board.Position;

public class PlaceShipCommand extends AbstractCommand {

    public PlaceShipCommand() {
        super("place", 3, "place:\t used when placing ships before the game. Choose start and endpoint of your ship. Example: place A3 C6");
    }

    public boolean matches(String keyword, String[] args) {
        return getKey().matches(keyword) && args.length == getArgsCount();
    }

    @Override
    public void execute(Game context, String[] args) {
        try {
            Position start = context.convertStringToPosition(args[1]);
            Position end = context.convertStringToPosition(args[2]);
            if (context.placeShip(start, end)) {
                context.printLine("Ship placed from " + start + " to " + end);
            } else {
                context.printLine("Invalid ship placement.");
            }
        } catch (IllegalArgumentException e) {
            context.printLine(e.getMessage());
        }
    }
}
