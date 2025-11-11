package org.example.model.board;


import lombok.Getter;
import org.example.model.ship.Ship;

public class Field {
    @Getter
    private State state;
    @Getter
    private Ship ship;
    private final Position position;

    public Field(Position position) {
        this.state = State.NONE;
        this.ship = null;
        this.position = position;
    }

    public String draw() {
        if (state == State.HIT)
            return "| X ";
        else if (ship != null)
            return "| " + ship.draw() + " ";
        else if (state == State.MISS)
            return "| O ";
        else
            return "| ~ ";
    }

    public String drawCoordinates() {
        return "|" + (char) (position.getColumn() + 'A') + position.getRow() + " ";
    }

    public boolean isOccupied() {
        return ship != null;
    }

    public void placeShip(Ship ship) {
        this.ship = ship;
    }

    public void markHit() {
        state = State.HIT;
    }

    public void markMiss() {
        state = State.MISS;
    }

}
