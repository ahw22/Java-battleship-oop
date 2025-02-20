package org.example;

import org.example.model.ShipPart;

public class Field {
    private State state;
    private ShipPart shipPart;
    private boolean validShipPlacement;

    public Field() {
        this.state = State.NONE;
        this.shipPart = null;
        this.validShipPlacement = true;
    }

    public String toString() {
        if (shipPart != null) {
            return "| " + shipPart.toString();
        } else {
            return "| " + state.toString() + " ";
        }
    }

    public boolean isValidShipPlacement() {
        return validShipPlacement;
    }

    public void setValidShipPlacement(boolean validShipPlacement) {
        this.validShipPlacement = validShipPlacement;
    }
}
