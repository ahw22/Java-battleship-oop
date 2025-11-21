package org.example.model.ship;

public class Submarine extends Ship{

    public Submarine() {
        HP = 2;
        name = "Submarine";
    }

    public String draw() {
        return "S";
    }
}
