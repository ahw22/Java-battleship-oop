package org.example;

public class ShipPart {
    private boolean hit;
    private Ship ship;

    public ShipPart(Ship ship) {
        this.hit = false;
        this.ship = ship;
    }

    public String toString() {
        return ship.toString();
    }
}
