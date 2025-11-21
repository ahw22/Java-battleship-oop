package org.example.model.ship;

public class Destroyer extends Ship{

    public Destroyer() {
        HP = 3;
        name = "Destroyer";
    }

    public String draw() {
        return "D";
    }
}
