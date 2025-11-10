package org.example.commands;

import org.example.game.GameContext;

public class PlaceShipCommand implements Command {
    private final String start;
    private final String end;

    public PlaceShipCommand(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(GameContext context) {
        if (context.placeShip(start, end)) {
            System.out.println("Ship placed from " + start + " to " + end);
        } else {
            System.out.println("Invalid ship placement.");
        }
    }
}
