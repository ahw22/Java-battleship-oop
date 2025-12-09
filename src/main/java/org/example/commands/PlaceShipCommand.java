package org.example.commands;

import org.example.game.GameContext;
import org.example.model.board.Position;

public class PlaceShipCommand extends AbstractCommand {

    public PlaceShipCommand() {
        this.key = "place";
        this.argsCount = 3;
        this.helpText = "place:\t used when placing ships before the game. Choose start and endpoint of your ship. Example: place A3 C6";
    }

    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
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

    @Override
    public String getHelpText() {
        return helpText;
    }
}
