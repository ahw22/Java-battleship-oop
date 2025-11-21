package org.example.model.ship;

public class Battleship extends Ship {

    public Battleship() {
        HP = 4;
        name = "Battleship";
    }

    public String draw() {
        return "B";
    }
}
