package org.example.commands;

import lombok.AllArgsConstructor;
import org.example.game.GameContext;
import org.example.model.board.Position;

@AllArgsConstructor
public class PlaceShipCommand implements Command {
    private final Position start;
    private final Position end;

    @Override
    public void execute(GameContext context) {
        if (context.placeShip(start, end)) {
            System.out.println("Ship placed from " + start + " to " + end);
        } else {
            System.out.println("Invalid ship placement.");
        }
    }
}
